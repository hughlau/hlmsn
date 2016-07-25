package com.hl.aop;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.dao.DataAccessException;

import com.hl.exception.BusinessException;

public class ServiceInterceptor {

	public void before(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		System.out.println(joinPoint.getSignature().getName());
		System.out.println("----------before()----------");
	}

	public void after(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		System.out.println(joinPoint.getSignature().getName());
		System.out.println("----------after()----------");
	}



	public void afterThrowing(JoinPoint joinPoint,Exception ex) {
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		System.out.println(joinPoint.getSignature().getName());
		System.out.println("----------afterThrowing()----------");
		
		// 在后台中输出错误异常异常信息，通过log4j输出。  
        Logger log = Logger.getLogger(joinPoint.getTarget().getClass().getSimpleName());  
        log.info("**************************************************************");  
        log.info("Error happened in class: " + joinPoint.getTarget().getClass().getSimpleName());  
        log.info("Error happened in method: " + joinPoint.getSignature().getName());  
            for (int i = 0; i < args.length; i++)  
            {  
                log.info("arg[" + i + "]: " + args[i]);  
            }  
        log.info("Exception class: " + ex.getClass().getName());  
        log.info("ex.getMessage():" + ex.getMessage());  
        ex.printStackTrace();  
        log.info("**************************************************************");  
  
        ex.printStackTrace();
        throw new BusinessException(ex.getClass().getName());
        

	}
}
