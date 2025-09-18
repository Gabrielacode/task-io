package com.garbi.taskio.services;

import com.garbi.taskio.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class JWTService {
    public static final String  EMAIL_CLAIM = "email";
    //This service will be in charge of  encoding and decoding jwt
    @Value("${jwt.secret.key}")
    String jwtKey;

    @Value("${jwt.expiration.time}")
     int timeToLiveInSeconds;
    //We will need a way to  store our security key
    //We will pass it using environmental variables


    //We will need to generate the key
    private SecretKey generateKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey));
    }
    public String createJWTForUser(User user){
        var expirationTime = new Date();
        expirationTime = new Date( expirationTime.getTime()+timeToLiveInSeconds);

        return Jwts.builder()
                .subject(user.getUsername())
                .claim(EMAIL_CLAIM,user.getEmail())
                .expiration(expirationTime)
                .signWith(generateKey()).compact();
    }

    //It will throw exceptions and we will catch  them and return a custom response
    public String  parseJwtToUserName(String jwt) throws Exception{
        return Jwts.parser().verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload().getSubject();
    }


}
