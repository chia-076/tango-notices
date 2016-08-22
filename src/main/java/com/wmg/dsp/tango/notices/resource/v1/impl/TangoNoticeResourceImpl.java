package com.wmg.dsp.tango.notices.resource.v1.impl;

import com.wmg.dsp.tango.jazz.commons.bootstrap.exception.response.BusinessValidationException;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.PaginationInfoWrapper;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.RequestResponseUtils;
import com.wmg.dsp.tango.jazz.commons.shared.ServiceConstants;
import com.wmg.dsp.tango.jazz.commons.shared.validation.ValidationErrorEnum;
import com.wmg.dsp.tango.jazz.commons.utils.annotations.UserToken;
import com.wmg.dsp.tango.jazz.commons.utils.annotations.UserTokenAware;
import com.wmg.dsp.tango.notices.domain.cassandra.TangoNotice;
import com.wmg.dsp.tango.notices.domain.cassandra.TangoNoticeView;
import com.wmg.dsp.tango.notices.manager.view.TangoNoticeViewManager;
import com.wmg.dsp.tango.notices.manager.view.TangoNoticeViews;
import com.wmg.dsp.tango.notices.resource.v1.TangoNoticeResource;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Path(TangoNoticeResource.BASE_PATH)
@Component
public class TangoNoticeResourceImpl implements TangoNoticeResource {

    @Autowired
    private TangoNoticeViewManager tangoNoticeViewManager;

    private void generateIds(List<TangoNotice> testEntities) {
        for (TangoNotice tangoNotice : testEntities) {
            if (StringUtils.isEmpty(tangoNotice.getId())) {
                tangoNotice.setId(UUID.randomUUID());
            }
        }
    }

   /* @Override
    public Response getById(UUID entityId, String userToken) {
        TangoNotice tangoNotice = manager.getTangoNoticeById(entityId);
        if (tangoNotice == null) {
            throw new EntityNotFoundException(MessageFormat.format("", entityId));
        }
        return Response.ok(tangoNotice).build();
    }

    @Override
    public Response create(@Valid @ApiParam(value = "List of Notices", defaultValue = DEFAULT_VALUE) @NotNull @Size(min = 1, message = "Size Constraint Violation") List<TangoNotice> testEntities) {
        generateIds(testEntities);

        Collection<TangoNotice> saved = manager.saveTestEntities(testEntities);

        if (saved == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.CREATED).entity(saved).build();
    }*/

    @Override
    @UserTokenAware
    public Response getById(UUID entityId, String viewName, @UserToken String userToken) {
        switch (viewName) {
            case TangoNoticeViews.TangoNoticeView:
                TangoNoticeView tangoNoticeView = tangoNoticeViewManager.getById(entityId);
                return Response.ok(tangoNoticeView).build();
            default:
                throw new BusinessValidationException("Invalid view name");
        }
    }

    @Override
    @UserTokenAware
    public Response getByIds(Set<UUID> ids, String viewName, @UserToken String userToken) {
        switch (viewName) {
            case TangoNoticeViews.TangoNoticeView: {
                Collection<TangoNoticeView> personViews = tangoNoticeViewManager.getByIds(ids);
                return Response.ok(personViews).build();
            }
            default:
                throw new BusinessValidationException("Invalid view name");
        }
    }

    @Override
    public PaginationInfoWrapper<?> findByFilters(@ApiParam(value = "Find by Filter") List<String> filters, @ApiParam(value = "Which page to return. First page has number 1") @DecimalMin(value = "1", message = ValidationErrorEnum.PAGINATION_PAGE_MIN_STR) @QueryParam("page") @DefaultValue(RequestResponseUtils.DEFAULT_PAGE) Integer page, @Max(value = RequestResponseUtils.MAX_PAGE_LIMIT, message = ValidationErrorEnum.MAX_PAGE_LIMIT_STR) @Min(value = RequestResponseUtils.MIN_PAGE_LIMIT, message = ValidationErrorEnum.MIN_PAGE_LIMIT_STR) @ApiParam(value = "limit, max number of items per page (< 10000)") @QueryParam("limit") @DefaultValue(RequestResponseUtils.DEFAULT_LIMIT) Integer limit, @ApiParam(value = "sortBy parameter") @QueryParam("sortBy") String sortBy, @ApiParam(value = "List of fields to be returned") @QueryParam("requested_fields") String requestedFields, @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION) @ApiParam(value = "User token") String userToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PaginationInfoWrapper<?> findNoticeEntityByFilters(@ApiParam(value = "Find by Filter") List<String> filters, @ApiParam(value = "Which page to return. First page has number 1") @DecimalMin(value = "1", message = ValidationErrorEnum.PAGINATION_PAGE_MIN_STR) @QueryParam("page") @DefaultValue(RequestResponseUtils.DEFAULT_PAGE) Integer page, @Max(value = RequestResponseUtils.MAX_PAGE_LIMIT, message = ValidationErrorEnum.MAX_PAGE_LIMIT_STR) @Min(value = RequestResponseUtils.MIN_PAGE_LIMIT, message = ValidationErrorEnum.MIN_PAGE_LIMIT_STR) @ApiParam(value = "limit, max number of items per page (< 10000)") @QueryParam("limit") @DefaultValue(RequestResponseUtils.DEFAULT_LIMIT) Integer limit, @ApiParam(value = "sortBy parameter") @QueryParam("sortBy") String sortBy, @ApiParam(value = "List of fields to be returned") @QueryParam("requested_fields") String requestedFields, @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION) @ApiParam(value = "User token") String userToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PaginationInfoWrapper<?> findNoticeTypeByFilters(@ApiParam(value = "Find by Filter") List<String> filters, @ApiParam(value = "Which page to return. First page has number 1") @DecimalMin(value = "1", message = ValidationErrorEnum.PAGINATION_PAGE_MIN_STR) @QueryParam("page") @DefaultValue(RequestResponseUtils.DEFAULT_PAGE) Integer page, @Max(value = RequestResponseUtils.MAX_PAGE_LIMIT, message = ValidationErrorEnum.MAX_PAGE_LIMIT_STR) @Min(value = RequestResponseUtils.MIN_PAGE_LIMIT, message = ValidationErrorEnum.MIN_PAGE_LIMIT_STR) @ApiParam(value = "limit, max number of items per page (< 10000)") @QueryParam("limit") @DefaultValue(RequestResponseUtils.DEFAULT_LIMIT) Integer limit, @ApiParam(value = "sortBy parameter") @QueryParam("sortBy") String sortBy, @ApiParam(value = "List of fields to be returned") @QueryParam("requested_fields") String requestedFields, @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION) @ApiParam(value = "User token") String userToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PaginationInfoWrapper<?> findNoticeReasonByFilters(@ApiParam(value = "Find by Filter") List<String> filters, @ApiParam(value = "Which page to return. First page has number 1") @DecimalMin(value = "1", message = ValidationErrorEnum.PAGINATION_PAGE_MIN_STR) @QueryParam("page") @DefaultValue(RequestResponseUtils.DEFAULT_PAGE) Integer page, @Max(value = RequestResponseUtils.MAX_PAGE_LIMIT, message = ValidationErrorEnum.MAX_PAGE_LIMIT_STR) @Min(value = RequestResponseUtils.MIN_PAGE_LIMIT, message = ValidationErrorEnum.MIN_PAGE_LIMIT_STR) @ApiParam(value = "limit, max number of items per page (< 10000)") @QueryParam("limit") @DefaultValue(RequestResponseUtils.DEFAULT_LIMIT) Integer limit, @ApiParam(value = "sortBy parameter") @QueryParam("sortBy") String sortBy, @ApiParam(value = "List of fields to be returned") @QueryParam("requested_fields") String requestedFields, @HeaderParam(ServiceConstants.DEFAULT_AUTHORIZATION) @ApiParam(value = "User token") String userToken) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
