package com.jjmontenegrop.security.controller;

import com.jjmontenegrop.security.entities.dto.AuthenticationResponseDTO;
import com.jjmontenegrop.security.entities.dto.AuthenticationRequestDTO;
import com.jjmontenegrop.security.entities.dto.RegisterRequestDTO;
import com.jjmontenegrop.security.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO request
    ) {

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody AuthenticationRequestDTO request
    ) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
