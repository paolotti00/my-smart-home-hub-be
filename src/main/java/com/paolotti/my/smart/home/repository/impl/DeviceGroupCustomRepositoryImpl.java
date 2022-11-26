package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.repository.IDeviceGroupCustomRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.repository.entity.GroupDeviceEntity;
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
    public GroupDeviceEntity findById(String groupId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(groupId);
        query.addCriteria(criteria);
        return (GroupDeviceEntity) mongoTemplate.findById(groupId, GroupDeviceEntity.class);
    }
}
