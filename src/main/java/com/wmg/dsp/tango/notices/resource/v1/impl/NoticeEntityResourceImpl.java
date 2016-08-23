package com.wmg.dsp.tango.notices.resource.v1.impl;

import com.wmg.dsp.platform.security.Oauth2SecurityUtils;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.RequestResponseUtils;
import com.wmg.dsp.tango.jazz.commons.utils.UuidUtils;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wmg.dsp.tango.notices.repositories.cassandra.NoticeDataRepository;
import com.wmg.dsp.tango.notices.resource.v1.NoticeEntityResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;

@Component
@Scope("request")
@Path(NoticeEntityResource.BASE_PATH)
public class NoticeEntityResourceImpl implements NoticeEntityResource {

    private static final Logger log = LoggerFactory.getLogger(NoticeEntityResourceImpl.class);

    @Autowired
    NoticeDataRepository noticeDataRepository;

    @Override
    public Response create(Notice notice, String userToken) {
        Notice saved = new Notice();
        setupTangoNotice(notice);
        try {
            saved = noticeDataRepository.save(notice);
            updateElasticSearchRepo(saved);
        } catch (Exception e) {
            log.error(MessageFormat.format("Error on saving notice: {0}", notice), e);
        }

        return RequestResponseUtils.buildCreateResponse(saved);
    }

    private void setupTangoNotice(Notice notice) {
        if (notice.getId() == null) {
            notice.setId(UuidUtils.getUUID());
        }
        notice.setUserName(Oauth2SecurityUtils.getUsername());
        notice.setUserProfileId(Oauth2SecurityUtils.getRequestUserProfileId());
    }

    private void updateElasticSearchRepo(Notice saved) {
        //TODO: update elastic search
    }
}
