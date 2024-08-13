package com.jjmontenegrop.springboot.error;

import com.jjmontenegrop.springboot.error.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John", "Doe"));
        users.add(new User(2L, "Jane", "Doe"));
        users.add(new User(3L, "Mike", "Smith"));
        users.add(new User(4L, "Helen", "Gonzalez"));
        users.add(new User(5L, "Luis", "Gomez"));
        return users;
    }
}
