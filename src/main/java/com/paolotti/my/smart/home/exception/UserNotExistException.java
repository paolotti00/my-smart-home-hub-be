package com.paolotti.my.smart.home.exception;

public class UserNotExistException extends GenericException {

    public UserNotExistException(String userId, String userEmail) {
        super(
                (
                        ((userId != null && userEmail != null) ? "user with id " + userId + "and email " + userEmail + "not exists" :
                                userId != null ? ("user with id " + userId) :
                                        userEmail != null ? ("user with email " + userEmail) :
                                                "user not exist : user id and user mail are null")));
    }
};
