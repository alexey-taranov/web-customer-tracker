package ru.taranov.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut  declarations
    @Pointcut("execution(* ru.taranov.springdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* ru.taranov.springdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* ru.taranov.springdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("\n======> in @Before: Method: " + theMethod);

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("=====> argument: " + arg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    public void before(JoinPoint joinPoint, Object theResult) {
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("\n======> in @AfterReturning: from Method: " + theMethod);
        logger.info("=====> result: " + theResult);

    }
}
