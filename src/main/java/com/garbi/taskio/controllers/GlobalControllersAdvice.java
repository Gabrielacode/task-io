package com.garbi.taskio.controllers;

//We are using this controller advice to handle errors thrown by our controllers or that reach the service classes

import com.garbi.taskio.exceptions.TaskException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllersAdvice {
    //For validation exceptions, we will list the fields that need to re entered due to validation errors

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception){
        Map<String,String > response =  new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            var key =  ((FieldError)error).getField();
            var message = error.getDefaultMessage();
            response.put(key,message);
        });
        return ResponseEntity.badRequest().body(response);
    }
    //For our error message of our tasks we will just pass "errorMessage":message

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<?> handleTaskExceptions(TaskException exception){
        Map<String,String > response =  new HashMap<>();
        response.put("errorMessage",exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
    //Any other exception for now will be passed  through a string
    //SO that we can debug
   @ExceptionHandler(Exception.class)
   public ResponseEntity<?> handleAnyExceptions(Exception exception){
       Map<String,String > response =  new HashMap<>();
       response.put("errorMessage",exception.getMessage());
       return ResponseEntity.internalServerError().body(response);
   }

}
