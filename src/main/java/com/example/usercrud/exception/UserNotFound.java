package com.example.usercrud.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String errorMessage) {
        super(errorMessage);
    }
}
