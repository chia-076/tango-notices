package com.wmg.dsp.tango.notices.manager.externalservices;


import com.wmg.dsp.tango.jazz.commons.bootstrap.entrypoint.usertoken.SystemAuthTokenManager;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.externalservices.base.AbstractExternalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExternalServices extends AbstractExternalServices {
    public static final String CALLER_NAME = "tannoticessrv";

    @Autowired
    protected SystemAuthTokenManager systemAuthTokenManager;

    public ExternalServices() {
        super(CALLER_NAME);
    }
}
