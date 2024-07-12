package br.com.vote.api.controller.advice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApiErrorMessage {

	private String message;
	private String description;
	private Date timestamp;
	private String statusCode;
	private List<String> errors;
	
	public ApiErrorMessage() {
	
	}
	
	public ApiErrorMessage(String message, Date timestamp, String statusCode) {
		this.message = message;
		this.timestamp = timestamp;
		this.statusCode = statusCode;
	}
	
	public ApiErrorMessage(String message, String description, Date timestamp, String statusCode) {
		this.errors = new ArrayList<>();
		this.message = message;
		this.description = description;
		this.timestamp = timestamp;
		this.statusCode = statusCode;
	}
	
	public ApiErrorMessage(String message, String description, Date timestamp, String statusCode, List<String> errors) {
		this.message = message;
		this.description = description;
		this.timestamp = timestamp;
		this.statusCode = statusCode;
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
