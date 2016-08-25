package com.wmg.dsp.tango.notices.async;


import com.wmg.dsp.platform.configuration.AbstractClassPathConfigFactory;
import com.wmg.dsp.platform.configuration.LocalConfig;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.async.config.MessagingConfig;
import org.springframework.stereotype.Component;

import java.util.Properties;

@LocalConfig
@Component("messagingConfig")
public class LocalMessagingConfigFactory extends AbstractClassPathConfigFactory<MessagingConfig> {

    public static final String INPUT_QUEUE_PROPERTY = "messaging.input_queue";
    public static final String ERROR_QUEUE_PROPERTY = "messaging.error_queue";

    public LocalMessagingConfigFactory() {
        super("messaging");
    }

    @Override
    protected MessagingConfig getConfigInstance(Properties env) {
        return new MessagingConfig(env.getProperty(INPUT_QUEUE_PROPERTY),
                env.getProperty(ERROR_QUEUE_PROPERTY));

    }
}