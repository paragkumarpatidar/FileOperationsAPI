package com.example.file.operations.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * FileOperation Aspect for binding service and controller logging
 * 
 * @author Parag Patidar
 *
 */
@Component
@Aspect
public class FileOperationsAspect {

	@Autowired
	private FileOperationsAspectUtils aspectUtils;

	/**
	 * Around Advice for Service Method Logging
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 */
	@Around("(execution(* com.example.file..service..*.* (..)))()")
	public Object arroundLoggerAdviceService(ProceedingJoinPoint proceedingJoinPoint) {
		return aspectUtils.methodAdvice(proceedingJoinPoint);
	}

	/**
	 * Around Advice for Controller Method Logging
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 */
	@Around("(execution(* com.example.file..controller..* (..)))")
	public Object arroundLoggerAdviceController(ProceedingJoinPoint proceedingJoinPoint) {
		return aspectUtils.methodAdvice(proceedingJoinPoint);
	}
}
