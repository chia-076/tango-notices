package com.wmg.dsp.tango.notices.manager.externalservices;

import com.wmg.dsp.tango.notices.manager.externalservices.base.ServiceConnection;
import com.wmg.dsp.tango.notices.manager.externalservices.base.AbstractExternalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExternalServices extends AbstractExternalServices {

    public enum Names {
        DEALS
    }

    private Map<String, String> externalServicesMap;

    @Autowired
    private AppExternalServicesConfig appExternalServicesConfig;

    @PostConstruct
    public void init() {
        externalServicesMap = new HashMap<>();

        addService(Names.DEALS, "cloud.services.tango_bs_dealservice.connection.conn",
                                "cloud.services.tango_bs_dealservice.connection.version");
    }

    @Override
    protected ServiceConnection getConnection(String key) {
        return new ServiceConnection(externalServicesMap.get(key));
    }

    private void addService(Names serviceName, String connection, String version) {
        String conn = appExternalServicesConfig.getProperty(connection);
        String ver = appExternalServicesConfig.getProperty(version);

        externalServicesMap.put(serviceName.toString(), conn + "/" + ver);
    }
}
