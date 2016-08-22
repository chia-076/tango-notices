package com.wmg.dsp.tango.notices.resource.v1.impl;

import com.wmg.dsp.platform.security.Oauth2SecurityUtils;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.RequestResponseUtils;
import com.wmg.dsp.tango.jazz.commons.utils.UuidUtils;
import com.wmg.dsp.tango.notices.domain.cassandra.TangoNotice;
import com.wmg.dsp.tango.notices.repositories.cassandra.TangoNoticeDataRepository;
import com.wmg.dsp.tango.notices.resource.v1.TangoNoticeEntityResource;
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
@Path(TangoNoticeEntityResource.BASE_PATH)
public class TangoNoticeEntityResourceImpl implements TangoNoticeEntityResource {

    private static final Logger log = LoggerFactory.getLogger(TangoNoticeEntityResourceImpl.class);

    @Autowired
    TangoNoticeDataRepository tangoNoticeDataRepository;

    @Override
    public Response create(TangoNotice tangoNotice, String userToken) {
        TangoNotice saved = new TangoNotice();
        setupTangoNotice(tangoNotice);
        try {
            saved = tangoNoticeDataRepository.save(tangoNotice);
            updateElasticSearchRepo(saved);
        } catch (Exception e) {
            log.error(MessageFormat.format("Error on saving Tango notice: {0}", tangoNotice), e);
        }

        return RequestResponseUtils.buildCreateResponse(saved);
    }

    private void setupTangoNotice(TangoNotice tangoNotice) {
        if (tangoNotice.getId() == null) {
            tangoNotice.setId(UuidUtils.getUUID());
        }
        tangoNotice.setUserName(Oauth2SecurityUtils.getUsername());
        tangoNotice.setUserProfileId(Oauth2SecurityUtils.getRequestUserProfileId());
    }

    private void updateElasticSearchRepo(TangoNotice saved) {
        //TODO: update elastic search
    }
}
