package com.wmg.dsp.tango.notices.exception.data;

import java.util.Objects;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionBean {

	@JsonProperty("status")
	private int status;
	private String message;
	private String description;
	private String href;
	private UUID reference;

	public ExceptionBean() {
	}

	public ExceptionBean(int status, String message, String description, String href, UUID reference) {
		this.status = status;
		this.message = message;
		this.description = description;
		this.href = href;
		this.reference = reference;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String a) {
		this.message = a;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public UUID getReference() {
		return reference;
	}

	public void setReference(UUID reference) {
		this.reference = reference;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final ExceptionBean other = (ExceptionBean) obj;
		return Objects.equals(this.description, other.description) && Objects.equals(this.href, other.href)
				&& Objects.equals(this.message, other.message) && Objects.equals(this.reference, other.reference)
				&& Objects.equals(this.status, other.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, href, message, reference, status);
	}

	@Override
	public String toString() {
		return "ExceptionBean{" + "status='" + status + '\'' + ", message='" + message + '\'' + ", description='"
				+ description + '\'' + ", href='" + href + '\'' + ", reference=" + reference + '}';
	}
}
