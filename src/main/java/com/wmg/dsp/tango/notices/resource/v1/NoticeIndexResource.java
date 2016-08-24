package com.wmg.dsp.tango.notices.resource.v1;

import com.wmg.dsp.tango.jazz.commons.bootstrap.resource.v1.IndexResource;
import com.wordnik.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Api(value = NoticeIndexResource.BASE_PATH, description = "Index operations")
@Validated
@Produces(APPLICATION_JSON)
public interface NoticeIndexResource extends IndexResource {
    String BASE_PATH = "/index";

    @GET
    @Path("/info")
    @ApiOperation(value = "Reindex info", response = Response.class)
    @ApiResponses({@ApiResponse(code = 500, message = "If error happened on server")})
    @PreAuthorize("isAuthorized()")
    Response info() throws Exception;

    @POST
    @Path("/start")
    @ApiOperation(value = "Starts reindexing process", response = Response.class)
    @ApiResponses({@ApiResponse(code = 500, message = "If error happened on server")})
    @PreAuthorize("isAuthorized()")
    Response start(
            @ApiParam(value = "Mapping")
            String mapping) throws Exception;
}
