package com.garbi.taskio.security;

import com.garbi.taskio.services.JWTService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.List;

//We will just set up a security configuration that will just allow all requests
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final TaskUserDetailsService taskUserDetailsService;


    //Now lets register our user service to the security config

    @Bean
     public UserDetailsService userDetailsService(){
        return taskUserDetailsService;
    }
    //Our PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    //We will want to register our authentication providers
    //For this project we will just be using DaoAuthenticationProvider
    @Bean
    public AuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder){
        var provider = new DaoAuthenticationProvider(taskUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;

    }
    @Bean
    //Then we provide our authentication manager
    //We  will get all the auth providers available and add them to the manager
    public AuthenticationManager providerManager(List<AuthenticationProvider> providers){
        return new ProviderManager(providers);
    }

    //We want to insert the Jwt Filter so we will create a Bean for that
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter (JWTService jwtService,UserDetailsService userDetailsService){
        return new JWTAuthenticationFilter(jwtService,userDetailsService);
    }
    //The filter chain
    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http,JWTAuthenticationFilter jwtAuthenticationFilter) throws Exception {
         //We will not make sure that any request must be authenticated
        //And this project we will use Basic authentication and JWT authentication
       var httpModified =  http
               .anonymous(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
               . addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic((basic)->{

//                    basic.authenticationEntryPoint(new AuthenticationEntryPoint() {
//                        @Override
//                        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//                            response.getWriter().print(authException.getMessage());
//                        }
//                    });
                })

                .authorizeHttpRequests(matcher -> matcher
                      //Any request should be last in the authorizeRequest chain
                        .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/task-group/**").authenticated()
                                .anyRequest().authenticated()

                        //We will see what happ
                )
                //This any request can override the fact that you permitted any of th request s

                .sessionManagement((session)->{
                    session.sessionCreationPolicy(SessionCreationPolicy.NEVER);
                })

                .build();

       return httpModified;
    }
}
