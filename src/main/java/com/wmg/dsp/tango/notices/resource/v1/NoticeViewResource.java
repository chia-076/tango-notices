package com.wmg.dsp.tango.notices.resource.v1;

import com.wmg.dsp.tango.jazz.commons.bootstrap.util.PaginationInfoWrapper;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.RequestResponseUtils;
import com.wmg.dsp.tango.jazz.commons.shared.ServiceConstants;
import com.wmg.dsp.tango.jazz.commons.shared.validation.ValidationErrorEnum;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wmg.dsp.tango.notices.manager.view.NoticeViews;
import com.wordnik.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Api(value = NoticeViewResource.BASE_PATH, description = "Tango Notices resource")
@Validated
@Produces(APPLICATION_JSON)
public interface NoticeViewResource {

    String BASE_PATH = "/notices";

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @ApiOperation(value = "Returns notice view by id", response = Response.class)
    @PreAuthorize("isAuthorized()")
    Response getById(
            @ApiParam(value = "Notice id")
            @NotNull
            @PathParam("id")
            UUID entityId,

            @Pattern(regexp = "(?-i)NoticeView", message = ValidationErrorEnum.PATTERN_STR)
            @ApiParam(value = "View name (" + NoticeViews.TangoNoticeView + ")")
            @NotNull(message = ValidationErrorEnum.NOT_NULL_STR)
            @QueryParam("view_name")
            String viewName,

            @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION)
            @ApiParam(value = "User token")
            String userToken);

    @POST
    @Path("/ids")
    @Produces("application/json")
    @ApiOperation(value = "Returns notice views by ids", response = Response.class)
    @PreAuthorize("isAuthorized()")
    Response getByIds(
            @ApiParam(value = "Array of ids separated by comma. Example: "
                    + "[\"fe0e2815-eefc-4f7e-bbb0-adcb7c439a2f\", \"e17e6dea-cb51-4bd0-b194-26de3b1b8faa\"]")
            @NotNull(message = ValidationErrorEnum.NOT_NULL_STR)
            @Size(max = 100, message = ValidationErrorEnum.MAX_PAGE_LIMIT_STR)
            Set<UUID> ids,

            @Pattern(regexp = "(?-i)NoticeView", message = ValidationErrorEnum.PATTERN_STR)
            @ApiParam(value = "View name (" + NoticeViews.TangoNoticeView + ")")
            @NotNull(message = ValidationErrorEnum.NOT_NULL_STR)
            @QueryParam("view_name")
            String viewName,

            @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION)
            @ApiParam(value = "User token")
            String userToken);

    @POST
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns a list of paginated notices", response = PaginationInfoWrapper.class)
    @ApiResponses(@ApiResponse(code = 404, message = "If notice is not found"))
    @PreAuthorize("isAuthorized()")
    PaginationInfoWrapper<Notice> findByFilters(
            List<String> filters,

            Integer page,

            Integer limit,

            String sortBy,

            String requestedFields,

            String userToken
    );
}
