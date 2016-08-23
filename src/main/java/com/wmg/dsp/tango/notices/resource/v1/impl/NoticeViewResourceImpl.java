package com.wmg.dsp.tango.notices.resource.v1.impl;

import com.wmg.dsp.platform.persistence.cassandra.exceptions.CassandraRuntimeException;
import com.wmg.dsp.tango.jazz.commons.bootstrap.exception.response.BusinessValidationException;
import com.wmg.dsp.tango.jazz.commons.bootstrap.exception.response.EntityNotFoundException;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.search.common.AbstractSortableElasticSearchResource;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.search.common.ComparatorFactory;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.PaginationInfoWrapper;
import com.wmg.dsp.tango.jazz.commons.search.utils.ElasticSearchUtils;
import com.wmg.dsp.tango.jazz.commons.search.utils.FilterTree;
import com.wmg.dsp.tango.jazz.commons.utils.StringUtils;
import com.wmg.dsp.tango.jazz.commons.utils.annotations.UserToken;
import com.wmg.dsp.tango.jazz.commons.utils.annotations.UserTokenAware;
import com.wmg.dsp.tango.notices.domain.cassandra.NoticeView;
import com.wmg.dsp.tango.notices.domain.dto.NoticeDTO;
import com.wmg.dsp.tango.notices.domain.search.SearchNotice;
import com.wmg.dsp.tango.notices.manager.search.NoticeSearchManager;
import com.wmg.dsp.tango.notices.manager.view.NoticeViewManager;
import com.wmg.dsp.tango.notices.manager.view.NoticeViews;
import com.wmg.dsp.tango.notices.resource.v1.NoticeViewResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.*;

import static com.wmg.dsp.tango.jazz.commons.search.utils.ElasticSearchUtils.filterTreeToString;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
@Scope("request")
@Path(NoticeViewResource.BASE_PATH)
public class NoticeViewResourceImpl extends AbstractSortableElasticSearchResource implements NoticeViewResource {

    private static final Logger log = LoggerFactory.getLogger(NoticeViewResourceImpl.class);

    @Autowired
    private NoticeViewManager noticeViewManager;

    @Autowired
    private NoticeSearchManager noticeSearchManager;

    @Override
    @UserTokenAware
    public Response getById(UUID entityId, String viewName, @UserToken String userToken) {
        switch (viewName) {
            case NoticeViews.TangoNoticeView:
                NoticeView noticeView = noticeViewManager.getById(entityId);
                return Response.ok(noticeView).build();
            default:
                throw new BusinessValidationException("Invalid view name");
        }
    }

    @Override
    @UserTokenAware
    public Response getByIds(Set<UUID> ids, String viewName, @UserToken String userToken) {
        switch (viewName) {
            case NoticeViews.TangoNoticeView: {
                Collection<NoticeView> personViews = noticeViewManager.getByIds(ids);
                return Response.ok(personViews).build();
            }
            default:
                throw new BusinessValidationException("Invalid view name");
        }
    }

    @Override
    @UserTokenAware
    public PaginationInfoWrapper<NoticeDTO> find(String filter, Integer page, Integer limit, String sortBy, String requestedFields, String userToken) {
        FilterTree filterTree = constructFilterTree(filter);
        log.debug("Calling service 'find' with filter {}", filter != null ? filter : "");

        Page<NoticeDTO> result = internalFind(filterTree, page, limit, sortBy, requestedFields);
        validateContentNotNullOrEmpty(filterTreeToString(filterTree), result.getContent());
        return new PaginationInfoWrapper<>(result);
    }

    @Override
    @UserTokenAware
    public List<PaginationInfoWrapper<NoticeDTO>> findByFilters(List<String> filters, Integer page, Integer limit, String sortBy, String requestedFields, String userToken) {
        List<PaginationInfoWrapper<NoticeDTO>> results = new LinkedList<>();
        if (filters != null) {
            for (String filter : filters) {
                FilterTree filterTree = constructFilterTree(filter);
                log.info("looking for filter: " + filter);

                Page<NoticeDTO> result = internalFind(filterTree, page, limit, sortBy, requestedFields);
                results.add(new PaginationInfoWrapper<>(result));
            }
        }
        return results;
    }

    @Override
    public ComparatorFactory getComparatorFactory() {
        return null;
    }

    private Page<NoticeDTO> internalFind(FilterTree filterTree, Integer page, Integer limit, String sortBy, String requestedFields) {
        PageRequest pageRequest = getPageRequest(page, limit, sortBy);

        //fields will be filtered in cassandra using partial reads
        return noticeSearchManager.findSimple(filterTree, pageRequest, parseRequestedFields(requestedFields));
    }

    private FilterTree constructFilterTree(String filter) {
        FilterTree filterTree = null;
        if (!StringUtils.isBlank(filter)) {
            try {
                filterTree = ElasticSearchUtils.buildFilterTree(filter, SearchNotice.class);
                log.info("FilterTree constructed for search notices: " + filterTree.toString());
            } catch (Exception e) {
                log.error("Failed to retrieve notices by filter " + filter, e);
                throw new CassandraRuntimeException("Error occurred while searching for filter: " + filter, e);
            }
        }
        return filterTree;
    }

    private void validateContentNotNullOrEmpty(String filter, List<NoticeDTO> content) {
        if (content == null || isEmpty(content)) {
            log.debug("Notices with filter = {} was not found", filter);
            throw new EntityNotFoundException(MessageFormat.format("Notices with filter = {0} was not found", filter));
        }
    }
}
