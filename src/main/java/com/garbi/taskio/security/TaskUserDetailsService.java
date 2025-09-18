package com.garbi.taskio.security;

import com.garbi.taskio.repositories.UserRepository;
import com.garbi.taskio.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//This is our user details service class for the authentication providers to user
public class TaskUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      //We will get the user from the db if there is no we will throw username not found exception
        var userResult = userRepository.findUserByUsernameOrEmail(username);
        return  userResult.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

