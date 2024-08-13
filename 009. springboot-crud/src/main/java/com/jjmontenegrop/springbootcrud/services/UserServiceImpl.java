package com.jjmontenegrop.springbootcrud.services;

import com.jjmontenegrop.springbootcrud.entities.Role;
import com.jjmontenegrop.springbootcrud.entities.User;
import com.jjmontenegrop.springbootcrud.exceptions.UsernameAlreadyExistsException;
import com.jjmontenegrop.springbootcrud.repositories.RoleRepository;
import com.jjmontenegrop.springbootcrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleReposiotry;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleReposiotry, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleReposiotry = roleReposiotry;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        Optional<Role> optionalRoleUser = roleReposiotry.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleReposiotry.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
