package com.wmg.dsp.tango.notices.resource.v1;

import com.wmg.dsp.tango.jazz.commons.bootstrap.domain.event.ChangeSet;
import com.wmg.dsp.tango.jazz.commons.bootstrap.domain.event.Event;
import com.wmg.dsp.tango.jazz.commons.bootstrap.resource.v1.EventResource;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.ws.rs.core.Response;
import java.util.UUID;

public interface SecureEventResource extends EventResource {
    @PreAuthorize("isAuthorized()")
    Response saveChangeSet(ChangeSet changeSet, Boolean async, String userToken);

    @PreAuthorize("isAuthorized()")
    Response saveEvent(Event event, Boolean async, String userToken);

    @PreAuthorize("isAuthorized()")
    Response replayEventsByRootEntityId(UUID rootEntityId, String userToken);

    @PreAuthorize("isAuthorized()")
    Response getEventsByEntityId(UUID entityId, String userToken);

    @PreAuthorize("isAuthorized()")
    Response getEventById(UUID rootEventId, UUID eventId, String userToken);

    @PreAuthorize("isAuthorized()")
    Response getEventsByChangeSetId(UUID changeSetId, String userToken);
}
