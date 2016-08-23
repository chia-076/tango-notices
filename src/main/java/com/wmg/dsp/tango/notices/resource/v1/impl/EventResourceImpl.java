package com.wmg.dsp.tango.notices.resource.v1.impl;

import com.wmg.dsp.tango.jazz.commons.bootstrap.resource.v1.EventResource;
import com.wmg.dsp.tango.jazz.commons.bootstrap.resource.v1.impl.AbstractEventResourceImpl;
import com.wmg.dsp.tango.notices.resource.v1.SecureEventResource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

@Component
@Scope("request")
@Path(EventResource.BASE_PATH)
public class EventResourceImpl extends AbstractEventResourceImpl implements SecureEventResource {
}
