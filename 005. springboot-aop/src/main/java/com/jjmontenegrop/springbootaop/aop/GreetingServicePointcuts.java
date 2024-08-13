package com.jjmontenegrop.springbootaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointcuts {

    // Este metodo declara un punto de corte para los metodos de la clase GreetingService
    @Pointcut("execution(* com.jjmontenegrop.springbootaop.services.GreetingService.*(..))")
    public void greetingLoggerPointcut() {
    }

    // Este metodo declara un punto de corte para los metodos de la clase GreetingService
    @Pointcut("execution(* com.jjmontenegrop.springbootaop.services.GreetingService.*(..))")
    public void greetingFooAspectPointcut() {}

}
