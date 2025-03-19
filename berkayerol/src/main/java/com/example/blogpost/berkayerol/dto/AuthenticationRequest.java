package com.example.blogpost.berkayerol.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Valid
    @NotBlank(message = "e-mail not be null")
    @Size(min = 5, max=50)
    private String email;

    @NotBlank(message = "password not be null")
    @Size(min = 4, max=30)
    private String password;
}
