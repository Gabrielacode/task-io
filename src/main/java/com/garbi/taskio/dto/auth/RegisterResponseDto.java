package com.garbi.taskio.dto.auth;

public record RegisterResponseDto(
        Integer id,
        String username,
        String password
) {
}
