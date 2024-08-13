package com.jjmontenegrop.springboot.error.services;

import com.jjmontenegrop.springboot.error.models.domain.User;

import java.util.List;

// La interfaz es el contrato que se va a implementar en la clase UserServiceImpl
public interface UserService {

    // Métodos que se van a implementar en la clase UserServiceImpl
    List<User> findAll();
    User findById(Long id);
}
