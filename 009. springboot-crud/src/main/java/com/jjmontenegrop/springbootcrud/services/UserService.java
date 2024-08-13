package com.jjmontenegrop.springbootcrud.services;

import com.jjmontenegrop.springbootcrud.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);
}
