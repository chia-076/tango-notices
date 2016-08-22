package com.wmg.dsp.tango.notices.resource.v1.notice;

import com.wmg.dsp.tango.jazz.commons.bootstrap.util.PaginationInfoWrapper;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.RequestResponseUtils;
import com.wmg.dsp.tango.jazz.commons.shared.ServiceConstants;
import com.wmg.dsp.tango.jazz.commons.shared.validation.ValidationErrorEnum;
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

@Api(value = TangoNoticeResource.BASE_PATH, description = "Tango Notices resource")
@Validated
@Produces(APPLICATION_JSON)
public interface TangoNoticeResource {

    String BASE_PATH = "/notices";

    String DEFAULT_VALUE = "[\n" +
            "  {\n" +
            "    \"id\": \"10017c6e-5144-11e5-885d-feff819cdc9f\",\n" +
            "    \"value\": \"value\",\n" +
            "    \"money\": 25.15\n" +
            "  }\n" +
            "]";

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @ApiOperation(value = "Returns notice view by id", response = Response.class)
    @PreAuthorize("isAuthorized()")
    Response getById(
            @ApiParam(value = "TangoNotice id")
            @NotNull
            @PathParam("id")
            UUID entityId,

            @Pattern(regexp = "(?-i)NoticeView", message = ValidationErrorEnum.PATTERN_STR)
            @ApiParam(value = "View name (TangoNoticeView)")
            @NotNull(message = ValidationErrorEnum.NOT_NULL_STR)
            @QueryParam("notice_name")
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
            @ApiParam(value = "View name (TangoNoticeView)")
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
    PaginationInfoWrapper<?> findByFilters(
            @ApiParam(value = "Find by Filter")
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
            String userToken
    );

    @POST
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns a list of paginated notice entities", response = PaginationInfoWrapper.class)
    @ApiResponses(@ApiResponse(code = 404, message = "If notice entity is not found"))
    @PreAuthorize("isAuthorized()")
    PaginationInfoWrapper<?> findNoticeEntityByFilters(
            @ApiParam(value = "Find by Filter")
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
            String userToken
    );

    @POST
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns a list of paginated notice types", response = PaginationInfoWrapper.class)
    @ApiResponses(@ApiResponse(code = 404, message = "If notice type is not found"))
    @PreAuthorize("isAuthorized()")
    PaginationInfoWrapper<?> findNoticeTypeByFilters(
            @ApiParam(value = "Find by Filter")
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
            String userToken
    );

    @POST
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns a list of paginated notice reasons", response = PaginationInfoWrapper.class)
    @ApiResponses(@ApiResponse(code = 404, message = "If notice reason is not found"))
    @PreAuthorize("isAuthorized()")
    PaginationInfoWrapper<?> findNoticeReasonByFilters(
            @ApiParam(value = "Find by Filter")
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
            String userToken
    );
}
