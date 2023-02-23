package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.repository.IDeviceGroupCustomRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceGroupCustomRepositoryImpl implements IDeviceGroupCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public DeviceGroupEntity save(DeviceGroupEntity deviceGroupEntity) {
        return mongoTemplate.save(deviceGroupEntity);
    }

    @Override
    public DeviceGroupEntity findById(String groupId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(groupId);
        query.addCriteria(criteria);
        return (DeviceGroupEntity) mongoTemplate.findById(groupId, DeviceGroupEntity.class);
    }
}
