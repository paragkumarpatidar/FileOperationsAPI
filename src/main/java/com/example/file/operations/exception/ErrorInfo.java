package com.example.file.operations.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String errorMessage;

	@JsonIgnore
	private String statusCode;

	public ErrorInfo() {
		super();
	}

	public ErrorInfo(String statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public ErrorInfo(String errorCode, String errorMessage, String statusCode) {
		super();
		this.statusCode = statusCode;
		this.errorCode = errorCode;
		this.setErrorMessage(errorMessage);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@JsonIgnore
	public String getActualErrorCode() {
		return errorCode;
	}

	@JsonIgnore
	public String getActualErrorMessage() {
		return errorMessage;
	}

	public String toMessage() {
		return "ErrorInfo [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", statusCode=" + statusCode
				+ "]";
	}

	@Override
	public String toString() {
		return "{\"errorCode\":\"" + errorCode + "\",\"errorMessage\":\"" + errorMessage + "\",\"statusCode\":\""
				+ statusCode + "\"}";
	}

}
