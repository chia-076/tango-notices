package com.wmg.dsp.tango.notices.resource.v1.impl;

import com.wmg.dsp.tango.jazz.commons.bootstrap.exception.response.BusinessValidationException;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.PaginationInfoWrapper;
import com.wmg.dsp.tango.jazz.commons.utils.annotations.UserToken;
import com.wmg.dsp.tango.jazz.commons.utils.annotations.UserTokenAware;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wmg.dsp.tango.notices.domain.cassandra.NoticeView;
import com.wmg.dsp.tango.notices.manager.view.NoticeViewManager;
import com.wmg.dsp.tango.notices.manager.view.NoticeViews;
import com.wmg.dsp.tango.notices.resource.v1.NoticeViewResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Path(NoticeViewResource.BASE_PATH)
@Component
public class NoticeViewResourceImpl implements NoticeViewResource {

    @Autowired
    private NoticeViewManager noticeViewManager;

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
    public PaginationInfoWrapper<Notice> findByFilters(List<String> filters, Integer page, Integer limit, String sortBy, String requestedFields, String userToken) {
        return null;  //TODO: implement me
    }
}
