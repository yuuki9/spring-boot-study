package com.java.footballism.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class UserCheckAspect {
		
		
		
		@Before("execution(* com.java.footballism.controller.MemberController.*(..))" +
				"&& @annotation(com.java.footballism.UserCheck) ")
	    public void loggerBefore(JoinPoint joinPoint) {
	        System.out.println("AOP 테스트 완료 입니다.");
	        Object[] obj  = joinPoint.getArgs();
	        for(int i = 0; i< obj.length; i++) {
	        	System.out.println(obj[i].toString());
	        }
	    }
	 
		
	 
}
