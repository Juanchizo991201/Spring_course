package com.jjmontenegrop.springbootcrud.controllers;

import com.jjmontenegrop.springbootcrud.entities.User;
import com.jjmontenegrop.springbootcrud.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        return create(user, bindingResult);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        user.setAdmin(false);
        return create(user, bindingResult);
    }

    /*
     * * Validation method
     */

    private ResponseEntity<?> validation(BindingResult bindingResult) {
        Map< String, String> errors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(),"El campo " + error.getField() + " " + error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    private ResponseEntity<?> create(User user, BindingResult bindingResult) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
