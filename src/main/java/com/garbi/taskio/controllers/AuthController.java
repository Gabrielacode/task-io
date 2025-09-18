package com.garbi.taskio.controllers;

import com.garbi.taskio.dto.auth.LoginRequestDto;
import com.garbi.taskio.dto.auth.RegisterRequestDto;
import com.garbi.taskio.dto.auth.RegisterResponseDto;
import com.garbi.taskio.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //Then first method is to register a user

    @PostMapping("/auth/register")
    public RegisterResponseDto registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        return authService.registerUser(registerRequestDto);
    }

    @PostMapping("/auth/login")
    public String loginUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return  authService.loginUser(loginRequestDto);
    }
}
