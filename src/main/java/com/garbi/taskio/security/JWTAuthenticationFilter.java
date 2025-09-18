package com.garbi.taskio.security;

import com.garbi.taskio.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;

@RequiredArgsConstructor
public class JWTAuthenticationFilter  extends OncePerRequestFilter {
     private final JWTService jwtService;
     private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //We will want to get the  authentication token from  the bearer
        var token = request.getHeader("Authorization");
        //If there is no token  or there is an authentication then we pass
        if(token == null || SecurityContextHolder.getContext().getAuthentication() != null){
           filterChain.doFilter(request,response);
           return;
        }
        //Then we will check if there is a bearer substring
        if(token.startsWith("Bearer ")){
            var jwtToken = token.substring(7);
             //Then we will try and parse the  token if there is an error we will  pass a error response and return and not continue the filter

            try{
                var userName = jwtService.parseJwtToUserName(jwtToken);
                //After parsing  we want to see if the user is in the db
                var user = userDetailsService.loadUserByUsername(userName);
                //If we can load the user then we pass an authenticated Authentication object to the security context
                var authenticatedToken =  new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
            } catch (Exception e) {
                var body = new HashMap<>();
                body.put("error",e.getMessage());

                 var responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
                 response.setContentType("application/json");
                 response.setStatus(HttpStatus.UNAUTHORIZED.value());
                 response.getWriter().write(String.valueOf(body));
                 return;
            }
        }
        filterChain.doFilter(request,response);
    }
}
