package com.wmg.dsp.tango.notices.entrypoint;

import com.wmg.dsp.platform.AppLoader;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.wmg.dsp.tango.notices")
public class FactoryApplicationImpl implements AppLoader {
}
