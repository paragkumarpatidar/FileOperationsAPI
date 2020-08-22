package com.example.file.operations.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.file.operations.exception.FileOperationsExceptionHandler;

/***
 * FileOperationsAPI Filter
 * 
 * @author Parag Patidar
 *
 */
public class FileOperationsFilter extends OncePerRequestFilter {

	@Autowired
	private FileOperationsExceptionHandler exceptionHandler;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			exceptionHandler.handleFileException(e, request, response);
		}
	}

}
