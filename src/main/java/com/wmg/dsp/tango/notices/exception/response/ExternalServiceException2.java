package com.wmg.dsp.tango.notices.exception.response;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wmg.dsp.tango.notices.exception.data.ExceptionEnum;

public class ExternalServiceException2 extends ServiceException2 {

	protected static final Logger log = LoggerFactory.getLogger(ExternalServiceException2.class);

	private int httpStatus;

	public ExternalServiceException2(String message) {
		super(message, ExceptionEnum.EXTERNAL_SERVICE_EXCEPTION);
	}

	public ExternalServiceException2(String message, int httpStatus) {
		super(message, ExceptionEnum.EXTERNAL_SERVICE_EXCEPTION);
		this.httpStatus = httpStatus;
	}

	public ExternalServiceException2(String message, Response.Status status) {
		super(message, ExceptionEnum.EXTERNAL_SERVICE_EXCEPTION, status);
	}

	public ExternalServiceException2(Throwable e) {
		super(e, ExceptionEnum.EXTERNAL_SERVICE_EXCEPTION);
	}

	public ExternalServiceException2(String message, int httpStatus, Response.Status status) {
		super(message, ExceptionEnum.EXTERNAL_SERVICE_EXCEPTION, status);
		this.httpStatus = httpStatus;
	}

	@Override
	public Logger getLogger() {
		return log;
	}

	public int getHttpStatus() {
		return httpStatus;
	}
}
