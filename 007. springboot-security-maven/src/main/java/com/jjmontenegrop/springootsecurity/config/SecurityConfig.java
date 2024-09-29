package com.jjmontenegrop.springootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // configuration 1
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf().disable()
//                .authorizeHttpRequests()
//                    .requestMatchers("v1/index2").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .build();
//    }

// Configuration 2
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("v1/index2").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin()
                .successHandler(successHandler()) // URL a la que se redirige al hacer login
                .permitAll()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .build();
    }

    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> response.sendRedirect("v1/index");
    }
}
