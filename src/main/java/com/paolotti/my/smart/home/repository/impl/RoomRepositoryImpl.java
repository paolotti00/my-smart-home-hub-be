package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.repository.RoomRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class RoomRepositoryImpl implements RoomRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;
}
