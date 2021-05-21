package com.msjo.move_in_reservation.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.msjo.move_in_reservation.service.*.*(..))")
    private void componentMethodPointcut() {}

    @Before("componentMethodPointcut()")
    public void loggingBeforeService(JoinPoint joinPoint) {

        logger.info("========== START SERVICE LOGIC");
        logger.info("Location : " + joinPoint.getSignature());

    }

    @AfterReturning(value = "componentMethodPointcut()", returning = "object")
    public void loggingAfterService(JoinPoint joinPoint, Object object) {


        logger.info("========== SUCCESS SERVICE LOGIC");

    }

    @AfterThrowing(value = "componentMethodPointcut()", throwing = "exceptions")
    public void loggingAfterService(JoinPoint joinPoint, Exception exceptions) {

        logger.info("Exceptions : " + exceptions);
        logger.info("========== FAIL SERVICE LOGIC");

    }


}
