package com.wmg.dsp.tango.notices.exception.response;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wmg.dsp.tango.notices.exception.data.ExceptionEnum;

public class ServiceException2 extends GenericException2 {

	protected static final Logger log = LoggerFactory.getLogger(ServiceException2.class);

	public ServiceException2(String message, ExceptionEnum code) {
		super(message, code, Response.Status.INTERNAL_SERVER_ERROR);
	}

	public ServiceException2(Throwable e, ExceptionEnum code) {
		super(e, code, Response.Status.INTERNAL_SERVER_ERROR);
	}

	public ServiceException2(String message, ExceptionEnum code, Status status) {
		super(message, code, status);
	}

	public ServiceException2(Throwable e, ExceptionEnum code, Response.Status status) {
		super(e, code, status);
	}

	@Override
	public Logger getLogger() {
		return log;
	}
}
