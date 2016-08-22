package com.wmg.dsp.tango.notices.manager.externalservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.el.PropertyNotFoundException;

@Configuration
@PropertySource("classpath:/configuration/externalservices.properties")
public class AppExternalServicesConfig {
    
    @Autowired
    private Environment env;

    public String getProperty(String propertyName) {
        String propertyValue = env.getProperty(propertyName);
        
        if (propertyValue == null) {
            throw new PropertyNotFoundException(propertyName);
        }
        
        return propertyValue;
    }
}
