package com.wmg.dsp.tango.notices.manager.externalservices.impl;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.wmg.dsp.tango.du.commons.domain.admin.Admin;
import com.wmg.dsp.tango.jazz.commons.bootstrap.exception.response.ExternalServiceException;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.externalservices.base.AbstractDataSource;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.externalservices.base.ExternalServiceDataSource;
import com.wmg.dsp.tango.notices.manager.externalservices.AdminDataSource;
import com.wmg.dsp.tango.notices.manager.externalservices.configuration.du.ExternalServicesDataUtilitiesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.UUID;

@Component("adminDataSource")
@ExternalServiceDataSource(name = AdminDataSource.NAME)
public class AdminDataSourceImpl extends AbstractDataSource implements AdminDataSource {
    private static final Logger log = LoggerFactory.getLogger(AdminDataSourceImpl.class);

    @Autowired
    @Qualifier("externalServicesDataUtilitiesConfig")
    private ExternalServicesDataUtilitiesConfig externalServicesDataUtilitiesConfig;

    @Override
    public String getApiPath() {
        String conn = externalServicesDataUtilitiesConfig.getServiceConnection();
        String version = externalServicesDataUtilitiesConfig.getServiceVersion();
        return conn + "/" + version;
    }

    @Override
    public Admin getById(UUID id) {
        log.info("Get admin by id: " + id);

        systemAuthTokenManager.updateNewSystemTokenOnThreadLocal();

        Response response = null;
        try {
            response = externalServices.get(NAME, "admins/" + id, null, null, Response.Status.OK);

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

            String body = response.readEntity(String.class);
            return mapper.readValue(body, Admin.class);
        } catch (ExternalServiceException | IOException e) {
            handleError("Error getting admin with id: " + id, response, e);
            return null;
        }
    }

    @Override
    protected Logger getLogger() {
        return log;
    }
}
