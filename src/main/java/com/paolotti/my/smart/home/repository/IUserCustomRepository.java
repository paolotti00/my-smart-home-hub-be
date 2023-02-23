package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.UserEntity;

public interface IUserCustomRepository {
    UserEntity save (UserEntity userEntity);
    UserEntity getUserById(String userId);
    UserEntity getUserByEmail(String userEmail);
}
