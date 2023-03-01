package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.repository.ICommandCustomRepository;
import com.paolotti.my.smart.home.repository.entity.CommandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommandCustomRepositoryImpl implements ICommandCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;
}
