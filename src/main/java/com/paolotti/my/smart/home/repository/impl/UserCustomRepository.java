package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.repository.IUserCustomRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.repository.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserCustomRepository implements IUserCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public UserEntity getUserById(String userId) {
        return mongoTemplate.findById(new ObjectId(userId), UserEntity.class);
    }
}
