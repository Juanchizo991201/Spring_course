package com.jjmontenegrop.security.services;

import com.jjmontenegrop.security.entities.dto.AuthenticationResponseDTO;
import com.jjmontenegrop.security.entities.dto.AuthenticationRequestDTO;
import com.jjmontenegrop.security.entities.dto.RegisterRequestDTO;
import com.jjmontenegrop.security.entities.Role;
import com.jjmontenegrop.security.entities.User;
import com.jjmontenegrop.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(RegisterRequestDTO request) {

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }
}
