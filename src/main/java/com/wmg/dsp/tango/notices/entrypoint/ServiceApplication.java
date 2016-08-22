package com.wmg.dsp.tango.notices.entrypoint;

import com.wmg.dsp.platform.rest.jersey.DefaultJerseyExceptionHandler;
import com.wmg.dsp.platform.rest.jersey.UriParamExceptionHandler;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath(ServiceApplication.PATH)
public class ServiceApplication extends ResourceConfig {

    public static final String PATH = "/api/v1";

    public ServiceApplication() {
        // Register exception all handlers from jersey2-module except BadRequestExceptionHandler ,
        // since we override it with our ConstraintViolationExceptionMapper, which is more relevant for data services.
        // TODO this should be moved to jersey2-module after the module have an ability to disable its default exception handlers
        super(DefaultJerseyExceptionHandler.class, UriParamExceptionHandler.class);

        // init about endpoint
        packages("com.wmg.dsp.platform.rest.jersey.info");

        // swagger resource & provider
        packages("com.wmg.dsp.platform.swagger.resources");

        // swagger ui
        packages("com.wmg.dsp.platform.swaggerui");

        // rest-client
        packages("com.wmg.dsp.platform.rest");

        // Register everything from service's package
        packages("com.wmg.dsp.tango.notices");
    }
}
