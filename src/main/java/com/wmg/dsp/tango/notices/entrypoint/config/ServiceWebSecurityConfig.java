package com.wmg.dsp.tango.notices.entrypoint.config;

import com.wmg.dsp.platform.security.config.WebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//FIXME This config is required for current deployment scripts because they use /apiVersions endpoint,
//so this endpoint was added to our service (BasePathListingServlet.class) and in this config disables security for needed endpoints
@Configuration
@Order(92)
@EnableWebSecurity
public class ServiceWebSecurityConfig extends WebSecurityConfig {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/apiVersions", "/");
        super.configure(web);
    }
}