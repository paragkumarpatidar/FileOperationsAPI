package com.example.file.operations.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/***
 * Exception Handler for File Operations API
 * 
 * @author Parag Patidar
 *
 */
@Component
public class FileOperationsExceptionHandler {

	/**
	 * Handle Exception occurred in FileOperations API
	 * 
	 * @param e               Exception
	 * @param servletRequest  {@link HttpServletRequest}
	 * @param servletResponse {@link HttpServletResponse}
	 * @throws IOException
	 */
	public void handleFileException(Exception e, HttpServletRequest servletRequest, HttpServletResponse servletResponse)
			throws IOException {
		FileOperationsException fileException = FileOperationsException.generateResponseForInternalErrors(e);
		if (e instanceof FileOperationsException) {
			fileException = getExceptionData(e);
		} else if (e.getCause() instanceof FileOperationsException) {
			fileException = getExceptionData((FileOperationsException) e.getCause());
		}

		servletResponse.setContentType("application/json");
		servletResponse.setStatus(Integer.parseInt(fileException.getErrorInfo().getStatusCode()));
		servletResponse.getWriter().write(fileException.getErrorInfo().toString());
	}

	private FileOperationsException getExceptionData(Exception e) {
		if (((FileOperationsException) e).getErrorInfo() != null) {
			return (FileOperationsException) e;
		}
		return FileOperationsException.generateResponseForInternalErrors(e);
	}

}
