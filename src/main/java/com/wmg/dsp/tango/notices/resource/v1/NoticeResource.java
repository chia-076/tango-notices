package com.wmg.dsp.tango.notices.resource.v1;


import com.wmg.dsp.tango.jazz.commons.shared.ServiceConstants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Api(value = NoticeResource.BASE_PATH, description = "Tango Notice entities resource")
@Validated
@Produces(APPLICATION_JSON)
public interface NoticeResource {

    String BASE_PATH = "/entities";

    @GET
    @Path("/notice/{id}")
    @Produces("application/json")
    @ApiOperation(value = "Returns notice by id", response = Response.class)
    @PreAuthorize("isAuthorized()")
    Response getNoticeById(
            @ApiParam(value = "Entity id")
            @NotNull
            @PathParam("id")
            UUID entityId,

            @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION)
            @ApiParam(value = "User token")
            String userToken);

    @POST
    @Path("/notice")
    @Produces("application/json")
    @ApiOperation(value = "Returns notices by ids", response = Response.class)
    @PreAuthorize("isAuthorized()")
    Response getPersonsByIds(
            @ApiParam(value = "Entities ids")
            @NotNull
            List<UUID> entityIds,

            @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION)
            @ApiParam(value = "User token")
            String userToken);
}
