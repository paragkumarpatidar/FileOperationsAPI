package com.example.file.operations.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.file.operations.exception.FileOperationsException;
import com.example.file.operations.utilities.LoggerAttribute;
import com.example.file.operations.utilities.LoggerUtils;

/***
 * Utility class for {@link FileOperationsAspect}
 * 
 * @author parpatid
 *
 */
@Component
public class FileOperationsAspectUtils {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private LoggerUtils loggerUtils;

	@Autowired
	private LoggerAttribute loggerAttribute;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileOperationsAspectUtils.class);

	public Object methodAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		loggerUtils.populateLoggerData(proceedingJoinPoint.getSignature().getName(), request.getMethod(),
				request.getRequestURI());
		LOGGER.info("{\"Enter\":\"{}.{}()\",\"{}\"}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
				proceedingJoinPoint.getSignature().getName(), loggerAttribute);
		try {
			Object result = proceedingJoinPoint.proceed();
			loggerUtils.populateLoggerData(proceedingJoinPoint.getSignature().getName(), request.getMethod(),
					request.getRequestURI());
			LOGGER.info("{\"Exit\":\"{}.{}()\",\"{}\"}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
					proceedingJoinPoint.getSignature().getName(), loggerAttribute);
			return result;
		} catch (FileOperationsException e) {
			logException(e, proceedingJoinPoint);
			throw e;
		} catch (Throwable e) {
			FileOperationsException fileException = FileOperationsException.generateResponseForInternalErrors(e);
			logException(fileException, proceedingJoinPoint);
			throw fileException;
		}
	}

	private void logException(FileOperationsException e, ProceedingJoinPoint proceedingJoinPoint) {
		loggerUtils.populateLoggerData(proceedingJoinPoint.getSignature().getName(), request.getMethod(),
				request.getRequestURI());
		LOGGER.error("{\"Exception\":\"{}.{}()\",\"{}\",\"ErrorDetails\":{}}",
				proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(),
				loggerAttribute, e.getErrorInfo());
	}

}
