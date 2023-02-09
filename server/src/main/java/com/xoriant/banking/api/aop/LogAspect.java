package com.xoriant.banking.api.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.context.annotation.EnableAspectJAutoProxy;


//import org.aspectj.lang.annotation.Aspect;
import lombok.extern.slf4j.Slf4j;

@EnableAspectJAutoProxy
@Slf4j
@Aspect
public class LogAspect {
	    
	
     
	     @Around("execution(public String com.xoriant.banking.api.DTO.loginInfo.getUsername())")
		 public Object logAroundUserId(ProceedingJoinPoint jp) {
		     log.info("The Around Advice Before Log is created by Aspect");
		     System.out.println("The method aroundAdvice() before invokation of the method " + jp.getSignature() + " method");  
			Object obj = null;
			try {
				obj = jp.proceed();
			} catch (Throwable e) {

			}

			log.info("The Around Advice After Log is created by Aspect with value :" + obj);
			System.out.println("The method aroundAdvice() after invokation of the method " + jp.getSignature() + " method");  
			return obj;
		}
	     
	     
	
	
}