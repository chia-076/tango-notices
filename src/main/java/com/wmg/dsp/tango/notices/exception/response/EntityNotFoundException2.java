package com.wmg.dsp.tango.notices.exception.response;

import com.wmg.dsp.tango.notices.exception.data.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class EntityNotFoundException2 extends GenericException2 {
    protected static final Logger log = LoggerFactory.getLogger(EntityNotFoundException2.class);

    public EntityNotFoundException2(String message, ExceptionEnum reason, Response.Status status) {
        super(message, reason, status);
    }

    public EntityNotFoundException2(Throwable e, ExceptionEnum reason, Response.Status status) {
        super(e, reason, status);
    }

    public EntityNotFoundException2(String message, Throwable e, ExceptionEnum reason, Response.Status status) {
        super(message, e, reason, status);
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
