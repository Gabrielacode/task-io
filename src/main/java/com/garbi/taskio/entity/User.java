package com.garbi.taskio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//This is the user entity used to store a user  for authentication purposes
//This class will also be used as the UserDetails implementation for our security configuration


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Integer id;
    @Setter
    String username;
    //This column is unique and should only one email per user
    @Column(unique = true)
    @Getter
    @Setter
    String email;
    @Setter
    String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Every User will have the authority or role of user

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
