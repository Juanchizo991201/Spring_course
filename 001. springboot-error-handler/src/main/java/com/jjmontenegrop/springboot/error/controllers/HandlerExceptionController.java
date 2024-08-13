package com.jjmontenegrop.springboot.error.controllers;

import com.jjmontenegrop.springboot.error.exceptions.UserNotFoundException;
import com.jjmontenegrop.springboot.error.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Error> divisionByZero(Exception e) {

        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setError("Division by zero");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setDate(new java.util.Date());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Error> notFoundException(Exception e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setError("Api rest not Found");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setDate(new java.util.Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NumberFormatException.class)
    public Map<String, String> numberFormatException(Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", e.getMessage());
        error.put("error", "Number format exception");
        error.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.put("date", new Date().toString());
        return error;
    }

    @ExceptionHandler({NullPointerException.class, UserNotFoundException.class})
    public ResponseEntity<Error> nullPointerException(Exception e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setError("Null pointer exception");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setDate(new java.util.Date());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
