package com.bitcamp.sc.util.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class SampleAdvice {

    @Pointcut("execution(* com.bitcamp.sc..*.*(..))")
    private void allLog() {}

    @Pointcut("execution(* com.bitcamp.sc..service..*(..))")
    private void allService(){}

    @Pointcut("execution(* com.bitcamp.sc..controller..*(..))")
    private void allController(){}

    @Before("allController() || allService()")
    public void doBefore(JoinPoint joinPoint){
        log.info("signature = {}",joinPoint.getSignature());
    }
}
