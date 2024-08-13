package com.jjmontenegrop.springbootinterceptor.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/foo")
    public Map<String, String> foo() {
        return Collections.singletonMap("message", "Handler foo del controlador!");
    }

    @GetMapping("/bar")
    public Map<String, String> bar() {
        return Collections.singletonMap("message", "Handler bar del controlador!");
    }

    @GetMapping("/baz")
    public Map<String, String> baz() {
        return Collections.singletonMap("message", "Handler baz del controlador!");
    }

}
