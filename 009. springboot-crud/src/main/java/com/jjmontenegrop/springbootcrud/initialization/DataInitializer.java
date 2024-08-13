package com.jjmontenegrop.springbootcrud.initialization;

import com.jjmontenegrop.springbootcrud.entities.Role;
import com.jjmontenegrop.springbootcrud.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class DataInitializer {

    private final RoleRepository roleRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_ADMIN", null));
        }
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_USER", null));
        }
    }
}
