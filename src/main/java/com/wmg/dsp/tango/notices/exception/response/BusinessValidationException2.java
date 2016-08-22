package com.wmg.dsp.tango.notices.exception.response;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wmg.dsp.tango.notices.exception.data.ExceptionEnum;

public class BusinessValidationException2 extends GenericException2 {

	protected static final Logger log = LoggerFactory.getLogger(BusinessValidationException2.class);

	public BusinessValidationException2(String message) {
		super(message, ExceptionEnum.BUSINESS_VALIDATION_EXCEPTION, Response.Status.BAD_REQUEST);
	}

	public BusinessValidationException2(String message, Throwable e) {
		super(message, e, ExceptionEnum.BUSINESS_VALIDATION_EXCEPTION, Response.Status.BAD_REQUEST);
	}

	@Override
	public Logger getLogger() {
		return log;
	}
}
