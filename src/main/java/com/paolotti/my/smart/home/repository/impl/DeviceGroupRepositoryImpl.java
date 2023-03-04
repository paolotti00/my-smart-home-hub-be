package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.repository.DeviceGroupRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DeviceGroupRepositoryImpl implements DeviceGroupRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;
}
