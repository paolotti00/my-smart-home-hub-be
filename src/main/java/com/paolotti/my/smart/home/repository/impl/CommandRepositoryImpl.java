package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.repository.CommandRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CommandRepositoryImpl implements CommandRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;
}
