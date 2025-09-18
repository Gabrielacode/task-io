package com.garbi.taskio.repositories;

import com.garbi.taskio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    //We want to define getUserByUserNameorEmail
    //Because we want the user to be able to login in with either an email or username

    //We will write our own custom query
    @Query("SELECT u FROM User u where u.username = :usernameOrEmail OR u.email = :usernameOrEmail")
    Optional<User> findUserByUsernameOrEmail(String usernameOrEmail);

}
