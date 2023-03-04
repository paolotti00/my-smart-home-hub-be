package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class UserRepositoryImpl implements UserRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;
}
