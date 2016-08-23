package com.wmg.dsp.tango.notices.entrypoint;

import com.wmg.dsp.platform.AppLoader;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(ServiceApplication.PACKAGE)
@ImportResource(value = "classpath:beans.xml")
public class FactoryApplicationImpl implements AppLoader {
}
