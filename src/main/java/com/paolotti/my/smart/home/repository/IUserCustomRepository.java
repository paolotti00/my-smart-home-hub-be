package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.UserEntity;

public interface IUserCustomRepository {
    UserEntity getUserById(String userId);
}
