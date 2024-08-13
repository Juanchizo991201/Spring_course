package com.jjmontenegrop.springboothorario.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @GetMapping("/index")
    public ResponseEntity<?> index(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Welcome to the care system!");
        data.put("time", new Date());
        data.put("message", request.getAttribute("message"));
        return ResponseEntity.ok(data);
    }
}
