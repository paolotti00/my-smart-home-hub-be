package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.repository.IUserCustomRepository;
import com.paolotti.my.smart.home.repository.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserCustomRepository implements IUserCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public UserEntity save(UserEntity userEntity) {
        return mongoTemplate.save(userEntity);
    }

    @Override
    public UserEntity getUserById(String userId) {
        return mongoTemplate.findById(new ObjectId(userId), UserEntity.class);
    }

    @Override
    public UserEntity getUserByEmail(String userEmail) {
        Query query = new Query();
        Criteria criteria = Criteria.where("email").is(userEmail);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, UserEntity.class);
    }
}
