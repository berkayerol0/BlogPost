package com.example.blogpost.berkayerol.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.blogpost.berkayerol.dto.AuthenticationRequest;
import com.example.blogpost.berkayerol.dto.AuthenticationResponse;
import com.example.blogpost.berkayerol.dto.RegisterRequest;
import com.example.blogpost.berkayerol.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final View error;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest
    ) {
        logger.info("Attempting user registration : ", registerRequest.getEmail());
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest authenticationRequest
    ) {
       logger.info("Attempting user registration : ", authenticationRequest.getEmail());
       return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Error during processing: ", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        logger.error("Validation error: {}", ex.getMessage());
        ex.getBindingResult().getAllErrors().forEach((error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }));
        return errors;
    }

    @GetMapping("/deneme")
    public ResponseEntity<?> helloWorld(String hello) {
        System.out.println("Hello World");
        return ResponseEntity.ok("Hello World");
    }
}
