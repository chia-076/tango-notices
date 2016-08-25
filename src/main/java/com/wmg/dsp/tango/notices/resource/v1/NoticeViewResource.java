package com.wmg.dsp.tango.notices.resource.v1;

import com.wmg.dsp.tango.jazz.commons.bootstrap.util.PaginationInfoWrapper;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.RequestResponseUtils;
import com.wmg.dsp.tango.jazz.commons.shared.ServiceConstants;
import com.wmg.dsp.tango.jazz.commons.shared.validation.ValidationErrorEnum;
import com.wmg.dsp.tango.notices.domain.dto.NoticeDTO;
import com.wmg.dsp.tango.notices.manager.view.NoticeViews;
import com.wordnik.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Api(value = NoticeViewResource.BASE_PATH, description = "Tango Notices resource")
@Validated
@Produces(MediaType.APPLICATION_JSON)
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

            @Pattern(regexp = "(?-i)" + NoticeViews.NoticeView, message = ValidationErrorEnum.PATTERN_STR)
            @ApiParam(value = "View name (" + NoticeViews.NoticeView + ")")
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

            @Pattern(regexp = "(?-i)" + NoticeViews.NoticeView, message = ValidationErrorEnum.PATTERN_STR)
            @ApiParam(value = "View name (" + NoticeViews.NoticeView + ")")
            @NotNull(message = ValidationErrorEnum.NOT_NULL_STR)
            @QueryParam("view_name")
            String viewName,

            @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION)
            @ApiParam(value = "User token")
            String userToken);

    @GET
    @Path("/find")
    @Produces("application/json")
    @ApiOperation(value = "Returns notices along with pagination info",
            response = PaginationInfoWrapper.class)
    @ApiResponses({@ApiResponse(code = 404, message = "If notices are not found")})
    @PreAuthorize("isAuthorized()")
    PaginationInfoWrapper<NoticeDTO> find(
            @ApiParam(value = "Find By Filter")
            @QueryParam("filter")
            String filter,

            @ApiParam(value = "Which page to return. First page has number 1")
            @DecimalMin(value = "1", message = ValidationErrorEnum.PAGINATION_PAGE_MIN_STR)
            @QueryParam("page")
            @DefaultValue(RequestResponseUtils.DEFAULT_PAGE)
            Integer page,

            @Max(value = RequestResponseUtils.MAX_PAGE_LIMIT, message = ValidationErrorEnum.MAX_PAGE_LIMIT_STR)
            @Min(value = RequestResponseUtils.MIN_PAGE_LIMIT, message = ValidationErrorEnum.MIN_PAGE_LIMIT_STR)
            @ApiParam(value = "limit, max number of items per page (< 10000)")
            @QueryParam("limit")
            @DefaultValue(RequestResponseUtils.DEFAULT_LIMIT)
            Integer limit,

            @ApiParam(value = "sortBy parameter")
            @QueryParam("sortBy")
            String sortBy,

            @ApiParam(value = "List of fields to be returned")
            @QueryParam("requested_fields")
            String requestedFields,

            @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION)
            @ApiParam(value = "User token")
            String userToken);

    @POST
    @Path("/findByFilters")
    @Produces("application/json")
    @ApiOperation(value = "Returns a list of paginated notices",
            response = PaginationInfoWrapper.class)
    @ApiResponses({@ApiResponse(code = 404, message = "If notices are not found")})
    @PreAuthorize("isAuthorized()")
    List<PaginationInfoWrapper<NoticeDTO>> findByFilters(
            @ApiParam(value = "Find By Filter")
            List<String> filters,

            @ApiParam(value = "Which page to return. First page has number 1")
            @DecimalMin(value = "1", message = ValidationErrorEnum.PAGINATION_PAGE_MIN_STR)
            @QueryParam("page")
            @DefaultValue(RequestResponseUtils.DEFAULT_PAGE)
            Integer page,

            @Max(value = RequestResponseUtils.MAX_PAGE_LIMIT, message = ValidationErrorEnum.MAX_PAGE_LIMIT_STR)
            @Min(value = RequestResponseUtils.MIN_PAGE_LIMIT, message = ValidationErrorEnum.MIN_PAGE_LIMIT_STR)
            @ApiParam(value = "limit, max number of items per page (< 10000)")
            @QueryParam("limit")
            @DefaultValue(RequestResponseUtils.DEFAULT_LIMIT)
            Integer limit,

            @ApiParam(value = "sortBy parameter")
            @QueryParam("sortBy")
            String sortBy,

            @ApiParam(value = "List of fields to be returned")
            @QueryParam("requested_fields")
            String requestedFields,

            @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION)
            @ApiParam(value = "User token")
            String userToken);
}
