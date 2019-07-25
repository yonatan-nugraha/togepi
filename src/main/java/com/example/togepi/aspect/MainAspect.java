package com.example.togepi.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MainAspect {

    @Pointcut("execution(* com.example.togepi.service.impl.*.hello(..))")
    private void getHelloPointCut() {
    }

    @Before("getHelloPointCut()")
    public void getHelloBeforeAdvice() {
        log.info("Hello before");
    }

    @After("getHelloPointCut()")
    public void getHelloAfterAdvice() {
        log.info("Hello after");
    }

    @Around("getHelloPointCut()")
    public Object getHelloAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
            log.info("Hello around, return value = " + value);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return value + " asd";
    }
}
