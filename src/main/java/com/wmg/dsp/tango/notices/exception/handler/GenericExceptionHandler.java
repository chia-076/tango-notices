package com.wmg.dsp.tango.notices.exception.handler;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wmg.dsp.tango.notices.exception.data.ExceptionBean;
import com.wmg.dsp.tango.notices.exception.response.GenericException2;

@Provider
public class GenericExceptionHandler implements ExceptionMapper<GenericException2> {

	@Context
	private HttpHeaders headers;

	public Response toResponse(GenericException2 e) {
		// use logger from specific exception
		e.getLogger().error(e.getMessage(), e);

		ExceptionBean exceptionBean = new ExceptionBean(e.getStatus().getStatusCode(), e.getReason().getMessage(),
				e.getMessage() == null ? e.getReason().getDescription() : e.getMessage(), "No URL", e.getUUID());

		return Response.status(e.getStatus()).entity(exceptionBean).type(headers.getMediaType()).build();
	}

}