package com.example.file.operations.exception;

import com.example.file.operations.constants.FileConstants;

public enum ErrorCodeEnum {

	TECHNICAL_ERROR("001", FileConstants.TECHNICAL_ERROR, "500"),

	NO_FILE_EXISTS("002", FileConstants.FILE_DO_NOT_EXISTS, "400"),
	
	UNABLE_TO_DELETE_FILE("003", FileConstants.UNABLE_TO_DELETE_FILE, "500");

	private String errorCode;

	private String errorMessage;

	private String statusCode;

	/**
	 * Instantiates a new error code enum.
	 *
	 * @param errorCode          the error code
	 * @param errorMesssage      the error messsage
	 * @param detailErrorMessage the detail error message
	 * @param statusCode         the status code
	 */
	ErrorCodeEnum(String errorCode, String errorMesssage, String statusCode) {
		this.errorCode = errorCode;
		this.errorMessage = errorMesssage;
		this.statusCode = statusCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

}
