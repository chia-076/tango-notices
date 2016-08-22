package com.wmg.dsp.tango.notices.manager.externalservices.base;

public class ServiceConnection {
    private String apiPath;

    public ServiceConnection(String path) {
        apiPath = path;
    }

    public String getApiPath() {
        return apiPath;
    }

    @Override
    public String toString() {
        return "ServiceConnection{" +
                "apiPath=" + apiPath +
                '}';
    }

}