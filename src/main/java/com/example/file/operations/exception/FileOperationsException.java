package com.example.file.operations.exception;

/***
 * Exception class for FileOperations API
 * 
 * @author Parag Patidar
 *
 */
public class FileOperationsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final ErrorInfo errorInfo;

	public FileOperationsException(String message, ErrorInfo errorInfo) {
		super(message);
		this.errorInfo = errorInfo;
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	/**
	 * Populate {@link FileOperationsException} when certain exception occured
	 * 
	 * @param e             any internal exception
	 * @param errorCodeEnum ErrorCodeEnum for particular code
	 * @return
	 */
	public static FileOperationsException populateFileOperationsException(Exception e, ErrorCodeEnum errorCodeEnum) {
		ErrorInfo errorInfo = new ErrorInfo(errorCodeEnum.getErrorCode(), errorCodeEnum.getErrorMessage(),
				errorCodeEnum.getStatusCode());
		return new FileOperationsException(e.getMessage(), errorInfo);
	}

	/**
	 * Generate {@link FileOperationsException}
	 * 
	 * @param errorCodeEnum
	 * @return
	 */
	public static FileOperationsException populateFileOperationsException(ErrorCodeEnum errorCodeEnum) {
		ErrorInfo errorInfo = new ErrorInfo(errorCodeEnum.getErrorCode(), errorCodeEnum.getErrorMessage(),
				errorCodeEnum.getStatusCode());
		return new FileOperationsException(errorCodeEnum.getErrorMessage(), errorInfo);
	}

	/**
	 * Generate Internal Server Exception response
	 * 
	 * @param e
	 * @return
	 */
	public static FileOperationsException generateResponseForInternalErrors(Throwable e) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(ErrorCodeEnum.TECHNICAL_ERROR.getErrorCode());
		errorInfo.setErrorMessage(ErrorCodeEnum.TECHNICAL_ERROR.getErrorMessage());
		errorInfo.setStatusCode(ErrorCodeEnum.TECHNICAL_ERROR.getStatusCode());
		return new FileOperationsException(e.getMessage(), errorInfo);
	}
}
