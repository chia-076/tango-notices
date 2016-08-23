package com.wmg.dsp.tango.notices.resource.v1;


import com.wmg.dsp.tango.jazz.commons.shared.ServiceConstants;
import com.wmg.dsp.tango.jazz.commons.shared.validation.ValidationErrorEnum;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Api(value = NoticeEntityResource.BASE_PATH, description = "Tango Notice entities resource")
@Validated
@Produces(APPLICATION_JSON)
public interface NoticeEntityResource {

    String BASE_PATH = "/entities";

    @POST
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Saves Tango Notice", response = Response.class)
    Response create(
            @Valid
            @ApiParam(value = "Tango Notice json data", defaultValue = "") //TODO: fix me - json data
            @NotNull(message = ValidationErrorEnum.NOT_NULL_STR)
            Notice notice,

            @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION)
            @ApiParam(value = "User token")
            String userToken
    );
}
