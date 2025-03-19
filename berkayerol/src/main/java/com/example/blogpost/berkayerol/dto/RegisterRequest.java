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
public class RegisterRequest {
    @Valid
    @NotBlank(message = "First name not be null")
    @Size(min = 3, max = 30)
    private String firstName;

    @NotBlank(message = "Last name not be null")
    @Size(min = 2, max = 30)
    private String lastName;

    @NotBlank(message = "email not be null")
    @Size(min = 10, max = 40)
    private String email;

    @NotBlank(message = "password not be null")
    @Size(min = 6, max = 30)
    private String password;
}
