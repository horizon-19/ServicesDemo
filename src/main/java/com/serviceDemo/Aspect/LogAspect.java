package com.serviceDemo.Aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
@Aspect
public class LogAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
	
	@Pointcut("execution(* com.serviceDemo.Services.*.*(..))")
	private void logInOut(){
		// just pointer to point cut 
		//the rest is done by logRun method
	}
	
	//weaved the common log mechanism to services
	@Around("logInOut()")
	public Object logRun(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		LOGGER.info("Entry : Method {}.{}() with args = {}",proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(),Arrays.toString(Arrays.asList(proceedingJoinPoint.getArgs()).stream().map(e -> e.toString()).toArray()));
		Object result = proceedingJoinPoint.proceed();
		LOGGER.info("Exit: result of method {}.{}() = {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),proceedingJoinPoint.getSignature().getName(), result.toString());
		return result;
	}

}
