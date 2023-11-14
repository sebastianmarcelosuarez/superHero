package com.application.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TimeLoggerAOP {

	static final Logger logger =  LoggerFactory.getLogger(TimeLoggerAOP.class);

	@Around("@annotation(MyTimeLogger)")
	public Object executionTimeLogger(ProceedingJoinPoint joinPoint) throws Throwable {

		try {

			long startTime = System.currentTimeMillis();
			Object proceed = joinPoint.proceed();
			long executionTime = (System.currentTimeMillis() - startTime);

			logger.info(String.format("%s method was executed in %s milliseconds", joinPoint.getSignature(), executionTime));
			return proceed;
		}

	 catch (Throwable e) {
		logger.error(String.format("There was an error while calculating method execution time for %s error %s", joinPoint.getSignature(),
			e.getMessage()));
		return null;
	}
}
}
