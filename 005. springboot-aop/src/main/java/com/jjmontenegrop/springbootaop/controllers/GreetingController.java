package com.jjmontenegrop.springbootaop.controllers;

import com.jjmontenegrop.springbootaop.services.GreetingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class GreetingController {

    private GreetingServiceImpl greetingService;

    @Autowired
    public GreetingController(GreetingServiceImpl greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok(Collections.singletonMap("greeting", greetingService.sayHello("John", "How are you?")));
    }

    @GetMapping("/greetingError")
    public ResponseEntity<?> greetingError() {
        return ResponseEntity.ok(Collections.singletonMap("greeting", greetingService.sayHelloError()));
    }
}
