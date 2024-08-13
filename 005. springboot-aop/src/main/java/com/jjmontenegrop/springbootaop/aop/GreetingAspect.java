package com.jjmontenegrop.springbootaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    // Se crea un logger para la clase
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Este metodo se ejecuta antes de que se ejecute un metodo de la clase GreetingService
    @Before("GreetingServicePointcuts.greetingLoggerPointcut()")
    public void loggerBefore(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("BEFORE --> Method: " + method + " with args: " + args);
    }

    // Este metodo se ejecuta despues de que se ejecute un metodo de la clase GreetingService
    @After("GreetingServicePointcuts.greetingLoggerPointcut()")
    public void loggerAfter(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("AFTER --> Method: " + method + " with args: " + args);
    }

    // Este metodo se ejecuta despues de que se ejecute un metodo de la clase GreetingService y si no hay excepciones
    @AfterReturning("GreetingServicePointcuts.greetingLoggerPointcut()")
    public void loggerAfterReturning(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("AFTER RETURNING --> Method: " + method + " with args: " + args);
    }

    // Este metodo se ejecuta despues de que se ejecute un metodo de la clase GreetingService y si hay excepciones
    @AfterThrowing("GreetingServicePointcuts.greetingLoggerPointcut()")
    public void loggerAfterThrowing(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();

        logger.info("AFTER EXCEPTION --> Method: " + method);
    }

    // Este metodo se ejecuta antes y despues de que se ejecute un metodo de la clase GreetingService
    @Around("GreetingServicePointcuts.greetingLoggerPointcut()")
    public Object loggerAround (ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("AROUND --> Method: " + method + " with args: " + args);

        Object result = joinPoint.proceed();

        logger.info("AROUND --> Method: " + method + " with args: " + args + " result: " + result);

        return result;
    }
}

