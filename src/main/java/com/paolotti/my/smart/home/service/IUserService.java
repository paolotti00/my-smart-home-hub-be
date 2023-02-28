package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.UserNotExistException;
import com.paolotti.my.smart.home.model.User;

public interface IUserService {
    User create (User userDto);
    User getUserById(String userId) throws UserNotExistException;
    User getUserByEmail(String userEmail) throws UserNotExistException;
    User checkIfUserExistsAndRetrieve(String userId) throws UserNotExistException;
    User checkIfUserExistsByIdOrEmailAndRetrieve(String userId, String email) throws UserNotExistException;
}
