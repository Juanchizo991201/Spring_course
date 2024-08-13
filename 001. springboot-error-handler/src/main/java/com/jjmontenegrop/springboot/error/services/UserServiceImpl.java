package com.jjmontenegrop.springboot.error.services;

import com.jjmontenegrop.springboot.error.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final List<User> users;

    @Autowired
    public UserServiceImpl(List<User> users) {
        this.users = users;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }
}
