package com.jjmontenegrop.springboot.error.controllers;

import com.jjmontenegrop.springboot.error.exceptions.UserNotFoundException;
import com.jjmontenegrop.springboot.error.models.domain.User;
import com.jjmontenegrop.springboot.error.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    private final UserServiceImpl userServiceimpl;

    @Autowired
    public AppController(UserServiceImpl userServiceimpl) {
        this.userServiceimpl = userServiceimpl;
    }

    @GetMapping
    public String index() {

//        int value = 10/0;
        int value = Integer.parseInt("10x");
        System.out.println(value);
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable(name = "id") Long id) {

        User user = userServiceimpl.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }

        return user;
    }
}
