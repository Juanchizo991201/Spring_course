package com.jjmontenegrop.springbootcrud.security;

import com.jjmontenegrop.springbootcrud.security.filter.JwtAuthenticationFilter;
import com.jjmontenegrop.springbootcrud.security.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    public SpringSecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/users").permitAll()  // Allow all requests to "/users" endpoints
                        .requestMatchers(HttpMethod.POST, "/users/register").permitAll()  // Allow all requests to "/users" endpoints
                        .anyRequest().authenticated()  // Authenticate all other requests
                )
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))  // Add the JWT authentication filter
                .addFilter(new JwtValidationFilter(authenticationManager()))  // Add the JWT authentication filter
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF (ensure this is necessary for your application)
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Set session management to stateless
                )
                .build();
    }
}
