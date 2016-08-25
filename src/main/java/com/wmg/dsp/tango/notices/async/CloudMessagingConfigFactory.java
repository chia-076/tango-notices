package com.wmg.dsp.tango.notices.async;

import com.wmg.dsp.platform.configuration.AbstractCloudFoundryConfigFactory;
import com.wmg.dsp.platform.configuration.CloudConfig;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.async.config.MessagingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@CloudConfig
@Component("messagingConfig")
public class CloudMessagingConfigFactory extends AbstractCloudFoundryConfigFactory<MessagingConfig> {
    private static final Logger log = LoggerFactory.getLogger(CloudMessagingConfigFactory.class);

    public static final String TANGO_NOTICE_MESSAGING = "tango_notice_messaging";

    public CloudMessagingConfigFactory() {
        super(serviceInfo -> {
            String name = (String) serviceInfo.get("name");
            return name.equals(TANGO_NOTICE_MESSAGING);
        });
    }

    @Override
    protected MessagingConfig getConfigFromServiceInfo(Map<String, Object> serviceInfoMap) {
        Map<String, Object> credentials = (Map<String, Object>) serviceInfoMap.get("credentials");
        String inputQueue = (String) credentials.get("input_queue");
        String errorQueue = (String) credentials.get("error_queue");

        return new MessagingConfig(inputQueue, errorQueue);
    }
}
