package com.garbi.taskio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

//We will just set up a security configuration that will just allow all requests
@EnableWebSecurity
@Configuration
public class SecurityConfig {


    //The filter chain
    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
         //We will just enable anonymous authentication and permit all requests for now
        return http
                .csrf(AbstractHttpConfigurer::disable)
                 .anonymous(Customizer.withDefaults())
                .authorizeHttpRequests((matcher)->{
                    matcher.anyRequest().permitAll();
                })
                .build();
    }
}
