package com.wmg.dsp.tango.notices.exception.response;

import javax.ws.rs.core.Response;

import com.wmg.dsp.tango.notices.exception.data.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerException2 extends GenericException2 {

	protected static final Logger log = LoggerFactory.getLogger(ServerException2.class);

	public ServerException2(String message) {
		super(message, ExceptionEnum.SERVER_EXCEPTION, Response.Status.INTERNAL_SERVER_ERROR);
	}

	public ServerException2(Throwable e) {
		super(e, ExceptionEnum.SERVER_EXCEPTION, Response.Status.INTERNAL_SERVER_ERROR);
	}

	@Override
	public Logger getLogger() {
		return log;
	}
}
