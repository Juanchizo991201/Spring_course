package com.jjmontenegrop.springbootaop.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService{

    @Override
    public String sayHello(String person, String phrase) {
        String greeting = "Hello! " + person + ", " + phrase;
        System.out.println(greeting);
        return greeting;
    }

    @Override
    public String sayHelloError() {
        throw new RuntimeException("Error in sayHelloError method");
    }
}
