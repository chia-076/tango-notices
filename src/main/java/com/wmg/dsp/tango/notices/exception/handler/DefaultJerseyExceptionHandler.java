package com.wmg.dsp.tango.notices.exception.handler;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.wmg.dsp.tango.notices.exception.response.ServerException2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wmg.dsp.tango.notices.exception.data.ExceptionBean;

@Provider
public class DefaultJerseyExceptionHandler implements ExceptionMapper<Throwable> {

	private static final Logger log = LoggerFactory.getLogger(DefaultJerseyExceptionHandler.class);

	@Context
	private HttpHeaders headers;

	public Response toResponse(Throwable exception) {

		ServerException2 e = new ServerException2(exception);

		log.error(e.getMessage(), e);

		// call this in order to take the root cause of error
		String errorMessage = extractMessage(e);
		String description = errorMessage == null ? e.getReason().getDescription() : errorMessage;
		ExceptionBean exceptionBean = new ExceptionBean(e.getStatus().getStatusCode(), e.getReason().getMessage(),
				description, "No URL", e.getUUID());
		return Response.status(e.getStatus()).entity(exceptionBean).type(headers.getMediaType()).build();
	}

	private String extractMessage(final Throwable exception) {
		if (exception.getCause() == null) {
			return exception.getMessage();
		} else {
			return extractMessage(exception.getCause());
		}
	}
}
