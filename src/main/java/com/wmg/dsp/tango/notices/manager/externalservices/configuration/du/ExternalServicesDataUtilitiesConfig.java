package com.wmg.dsp.tango.notices.manager.externalservices.configuration.du;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalServicesDataUtilitiesConfig {
    private static final Logger log = LoggerFactory.getLogger(ExternalServicesDataUtilitiesConfig.class);

    private final String serviceConnection;
    private final String serviceVersion;

    public ExternalServicesDataUtilitiesConfig(String serviceConnection, String serviceVersion) {
        this.serviceConnection = serviceConnection;
        this.serviceVersion = serviceVersion;

        log.debug("Instantiating external services config - contacts: connection: {}, version: {}",
                serviceConnection, serviceVersion);
    }

    public String getServiceConnection() {
        return serviceConnection;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }
}
