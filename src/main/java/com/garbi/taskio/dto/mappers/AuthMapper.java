package com.garbi.taskio.dto.mappers;

import com.garbi.taskio.dto.auth.RegisterRequestDto;
import com.garbi.taskio.dto.auth.RegisterResponseDto;
import com.garbi.taskio.entity.User;

public class AuthMapper {
    public static User registerRequestToUser(RegisterRequestDto request){
        return new User(null, request.username(), request.email(), request.password());
    }

    public static RegisterResponseDto userToResponseDto(User user){
        return new RegisterResponseDto(user.getId(), user.getUsername(), user.getPassword());
    }
}
