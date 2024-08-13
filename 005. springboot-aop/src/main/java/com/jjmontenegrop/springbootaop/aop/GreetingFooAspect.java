package com.jjmontenegrop.springbootaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {



    // Se crea un logger para la clase
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Este metodo se ejecuta antes de que se ejecute un metodo de la clase GreetingService
    @Before("GreetingServicePointcuts.greetingLoggerPointcut()")
    public void loggerBefore(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("FOO BEFORE --> Method: " + method + "invoked with args: " + args);
    }

    // Este metodo se ejecuta despues de que se ejecute un metodo de la clase GreetingService
    @After("GreetingServicePointcuts.greetingLoggerPointcut()")
    public void loggerAfter(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("FOO AFTER --> Method: " + method + "invoked with args: " + args);
    }
}
