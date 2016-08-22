package com.wmg.dsp.tango.notices.entrypoint.usertoken;

import com.wmg.dsp.tango.notices.manager.externalservices.base.ServiceConstants;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class UserTokenPreFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationToken = requestContext.getHeaders().getFirst(ServiceConstants.DEFAULT_AUTHORIZATION);
        UserTokenHolder.set(authorizationToken);
    }
}
