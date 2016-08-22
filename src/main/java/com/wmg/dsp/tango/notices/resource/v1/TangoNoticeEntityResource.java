package com.wmg.dsp.tango.notices.resource.v1;


import com.wmg.dsp.tango.jazz.commons.shared.ServiceConstants;
import com.wmg.dsp.tango.jazz.commons.shared.validation.ValidationErrorEnum;
import com.wmg.dsp.tango.notices.domain.cassandra.TangoNotice;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Api(value = TangoNoticeEntityResource.BASE_PATH, description = "Tango TangoNotice entities resource")
@Validated
@Produces(APPLICATION_JSON)
public interface TangoNoticeEntityResource {

    String BASE_PATH = "/entities";

    String DEFAULT_VALUE = "[\n" +
            "  {\n" +
            "    \"id\": \"10017c6e-5144-11e5-885d-feff819cdc9f\",\n" +
            "    \"value\": \"value\",\n" +
            "    \"money\": 25.15\n" +
            "  }\n" +
            "]";

    @POST
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Saves List of Notices", response = Response.class)
    Response create(
            @Valid
            @ApiParam(value = "Array of ids separated by comma. Example: "
                    + "[\"fe0e2815-eefc-4f7e-bbb0-adcb7c439a2f\", \"e17e6dea-cb51-4bd0-b194-26de3b1b8faa\"]")
            @NotNull(message = ValidationErrorEnum.NOT_NULL_STR)
            @Size(min = 1, message = ValidationErrorEnum.MIN_STR)
            List<TangoNotice> testEntities,

            @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION)
            @ApiParam(value = "User token")
            String userToken
    );
}
