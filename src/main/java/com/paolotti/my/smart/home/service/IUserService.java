package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.UserNotExistException;
import com.paolotti.my.smart.home.model.User;

public interface IUserService {
    User getUserById(String userId) throws UserNotExistException;

    User checkIfUserExistsAndRetrieve(String userId) throws UserNotExistException;
}
