package com.example.blogpost.berkayerol.service;

import com.example.blogpost.berkayerol.dto.AuthenticationRequest;
import com.example.blogpost.berkayerol.dto.AuthenticationResponse;
import com.example.blogpost.berkayerol.dto.RegisterRequest;
import com.example.blogpost.berkayerol.enums.Role;
import com.example.blogpost.berkayerol.model.User;
import com.example.blogpost.berkayerol.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        logger.info("Registering user: {}", registerRequest.getEmail());

        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email is aldready in use ! ! ");
        }

        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateJwtToken(user);
        logger.info("User registered successfully: {}", registerRequest.getEmail());
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        logger.info("Authenticating user: {}", authenticationRequest.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateJwtToken(user);
        logger.info("User authenticated successfully: {}", authenticationRequest.getEmail());
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public User findUserByMail(String email) {
        logger.info("Finding user by email: {}", email);
        return userRepository.findByEmail(email).orElseThrow();
    }
}
