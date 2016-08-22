package com.wmg.dsp.tango.notices.exception.response;

import java.util.Objects;
import java.util.UUID;

import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wmg.dsp.tango.notices.exception.data.ExceptionEnum;

public class GenericException2 extends RuntimeException {

	protected static final Logger log = LoggerFactory.getLogger(GenericException2.class);

	private ExceptionEnum reason;
	private Status status;
	private UUID uuid;

	public GenericException2(String message, ExceptionEnum reason, Status status) {
		super(message);
		this.reason = reason;
		this.status = status;
		uuid = UUID.randomUUID();
	}

	public GenericException2(Throwable e, ExceptionEnum reason, Status status) {
		super(e);
		this.reason = reason;
		this.status = status;
		uuid = UUID.randomUUID();
	}

	public GenericException2(String message, Throwable e, ExceptionEnum reason, Status status) {
		super(message, e);
		this.reason = reason;
		this.status = status;
		uuid = UUID.randomUUID();
	}

	public ExceptionEnum getReason() {
		return reason;
	}

	public Status getStatus() {
		return status;
	}

	public UUID getUUID() {
		return uuid;
	}

	/**
	 * Method return specif logger of the entity that extends Should be overridden by classes that extend this class
	 * 
	 * @return the logger
	 */
	public Logger getLogger() {
		return log;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final GenericException2 other = (GenericException2) obj;
		return Objects.equals(this.getMessage(), other.getMessage())
				&& Objects.equals(this.getReason(), other.getReason())
				&& Objects.equals(this.getStatus(), other.getStatus());
	}

	public String toString() {
		Object reasonName = reason == null ? "" : reason.name();
		Object id = uuid == null ? "" : uuid.toString();
		Object stat = status == null ? "" : status.toString();

		return reasonName + " [" + id + " - " + stat + "]: " + getMessage();
	}

}
