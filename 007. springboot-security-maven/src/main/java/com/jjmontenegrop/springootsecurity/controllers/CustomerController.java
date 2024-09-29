package com.jjmontenegrop.springootsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class CustomerController {

    @GetMapping("/index")
    public String index() {
        return "Hello from CustomerController";
    }

    @GetMapping("/index2")
    public String index2() {
        return "Hello from CustomerController without security";
    }

}
