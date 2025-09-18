package com.garbi.taskio.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto (
        @NotBlank(message = "User name cannot be blank ")
        String usernameOrEmail ,
        @NotBlank(message = "Password cannot be blank ")
        String password
) {
}
