package com.garbi.taskio.services;

import com.garbi.taskio.dto.auth.LoginRequestDto;
import com.garbi.taskio.dto.auth.LoginResponseDto;
import com.garbi.taskio.dto.auth.RegisterRequestDto;
import com.garbi.taskio.dto.auth.RegisterResponseDto;
import com.garbi.taskio.dto.mappers.AuthMapper;
import com.garbi.taskio.entity.User;
import com.garbi.taskio.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    //This service will be in charge of handling all auth services
    //From registering , to login in to get an access token
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private  final JWTService jwtService;

    public RegisterResponseDto registerUser(RegisterRequestDto request){
        var userFromRequst = AuthMapper.registerRequestToUser(request);
        //We will need to encrypt the password
        userFromRequst.setPassword(passwordEncoder.encode(userFromRequst.getPassword()));
        //Then we save it
       var savedUser =  userRepository.save(userFromRequst);
       return  AuthMapper.userToResponseDto(savedUser);
    }
    //For the login we will need the authentication manager to authenticate the user
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto){
        var usernamePasswordAuth = new UsernamePasswordAuthenticationToken(loginRequestDto.usernameOrEmail(),loginRequestDto.password());
     //Then we authenticate
      usernamePasswordAuth = (UsernamePasswordAuthenticationToken) authenticationManager.authenticate(usernamePasswordAuth);
   //If there is an error authenticating then it will throw an exception
    // We will then return a jwt to the user
        var user = (User)usernamePasswordAuth.getPrincipal();
         var token = jwtService.createJWTForUser(user);

         return  new LoginResponseDto(user.getUsername(),token);
    }
}
