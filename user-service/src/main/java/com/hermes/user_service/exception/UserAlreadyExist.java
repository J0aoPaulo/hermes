package com.hermes.user_service.exception;

public class UserAlreadyExist extends RuntimeException {

    public UserAlreadyExist(String message) {
        super(message);
    }
}
