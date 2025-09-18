package com.garbi.taskio.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//This dto is for accepting user registration requests
public record RegisterRequestDto(
        @NotBlank(message = "User name cannot be blank ")
        String username,
        @Email(message = "Email should be valid")
        String email,
        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
