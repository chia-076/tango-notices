package com.wmg.dsp.tango.notices.exception.data;

/**
 * This is used to define the exceptional cases
 */
public enum ExceptionEnum {

	SERVER_EXCEPTION("Critical error", "Server exception."), 
	ENTITY_NOT_FOUND("Entity not found", "Searched entity could not be found using provided data."), 
	DATA_VALIDATION_EXCEPTION("Data validation error", "A data validation condition was not met."), 
	BUSINESS_VALIDATION_EXCEPTION("Business validation error", "A business validation condition was not completed successfully."), 
	EXTERNAL_SERVICE_EXCEPTION("External service error", "Error occurred when contacting external service.");

	private String message;
	private String description;

	private ExceptionEnum(String message, String description) {
		this.message = message;
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}
}
