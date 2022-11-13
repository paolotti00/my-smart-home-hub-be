package com.paolotti.my.smart.home.exception;

public class UserNotExistException extends GenericException{

    public UserNotExistException(String userId) {
        super("userId " + userId + "not exist");
    }
}
