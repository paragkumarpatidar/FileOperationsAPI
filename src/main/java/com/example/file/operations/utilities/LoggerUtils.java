package com.example.file.operations.utilities;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * LoggerUtils class which support {@link LoggerAttribute}
 * 
 * @author Parag Patidar
 *
 */
@Component
public class LoggerUtils {

	@Autowired
	private LoggerAttribute loggerAttribute;

	@Value("${spring.application.name}")
	private String apiId;

	public LoggerAttribute populateLoggerData(String methodName, String methodType, String path) {
		loggerAttribute.setApiId(apiId);
		loggerAttribute.setRequestId(UUID.randomUUID());
		loggerAttribute.setPath(path);
		loggerAttribute.setMethodType(methodType);
		loggerAttribute.setTimeStamp(Instant.now().toString());
		loggerAttribute.setOperationName(methodName);
		return loggerAttribute;
	}

}
