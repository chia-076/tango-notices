package com.wmg.dsp.tango.notices.entrypoint;

import com.wmg.dsp.tango.jazz.commons.bootstrap.entrypoint.BaseServiceApplication;

import javax.ws.rs.ApplicationPath;

@ApplicationPath(ServiceApplication.PATH)
public class ServiceApplication extends BaseServiceApplication {

    public static final String PATH = "/api/v1";
    public static final String PACKAGE = "com.wmg.dsp.tango.notices";

    @Override
    public String getServicePackage() {
        return PACKAGE;
    }
}
