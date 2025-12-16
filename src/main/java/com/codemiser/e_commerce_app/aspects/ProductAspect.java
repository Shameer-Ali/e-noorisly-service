package com.codemiser.e_commerce_app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProductAspect {
    public static final Logger logger = LoggerFactory.getLogger(ProductAspect.class);

    @Before("execution(* com.codemiser.e_commerce_app.services.ProductServices.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("products method before exexution logged ::" + joinPoint.getSignature().getName());
    }

    @Around("execution(* com.codemiser.e_commerce_app.services.ProductServices.addProducts(..))")
    public Object logAddProduct(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        long resultDiffTime = endTime - startTime;
        logger.info("resultDiffTime  :: {} milliseconds.", resultDiffTime);
        return result;
    }

    @After("execution(* com.codemiser.e_commerce_app.services.ProductServices.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("products method logged after execution ::" + joinPoint.getSignature().getName());
    }
}
