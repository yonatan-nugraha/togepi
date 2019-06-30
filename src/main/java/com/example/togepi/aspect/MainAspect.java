package com.example.togepi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MainAspect {

    private static final Logger logger = LoggerFactory.getLogger(MainAspect.class.getName());

    @Pointcut("execution(* com.example.togepi.service.impl.*.hello(..))")
    private void getHelloPointCut() {
    }

    @Before("getHelloPointCut()")
    public void getHelloBeforeAdvice() {
        logger.info("Hello before");
    }

    @After("getHelloPointCut()")
    public void getHelloAfterAdvice() {
        logger.info("Hello after");
    }

    @Around("getHelloPointCut()")
    public Object getHelloAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
            logger.info("Hello around, return value = " + value);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return value + " asd";
    }
}
