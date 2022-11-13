package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.repository.IDeviceCustomRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;

@Repository
public class DeviceCustomRepositoryImpl implements IDeviceCustomRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public DeviceEntity save(DeviceEntity deviceEntity) {
        return mongoTemplate.save(deviceEntity);
    }

    @Override
    public ArrayList<DeviceEntity> findAllByMacAddressAndNotDeactivated(String macAddress) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria = Criteria.where("networkData.macAddress").is(macAddress).and("installationStatus").ne(DeviceEntity.DeviceInstallationStatusEnum.DEACTIVATED);
        query.addCriteria(criteria);
        return new ArrayList<>(mongoTemplate.find(query, DeviceEntity.class));
    }
    @Override
    public ArrayList<DeviceEntity> findAllByUserAndToActivate(String userId){
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria = Criteria.where("user._id").is(new ObjectId(userId)).and("installationStatus").is(DeviceEntity.DeviceInstallationStatusEnum.TO_ACTIVATE);
        query.addCriteria(criteria);
        return new ArrayList<>(mongoTemplate.find(query, DeviceEntity.class));
    }
}
