package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.repository.IDeviceCustomRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class DeviceCustomRepositoryImpl implements IDeviceCustomRepository {
    // all methods, if not differently specified, check silently also if the status is ACTIVE
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public  ArrayList<DeviceEntity> findAllByMacAddressAndNotDeactivated(String macAddress) {
        Query query = new Query();
        Criteria criteria = Criteria.where("networkData.macAddress").is(macAddress).and("installationStatus").ne(DeviceInstallationStatusEnum.DEACTIVATED);
        query.addCriteria(criteria);
        return new ArrayList<>(mongoTemplate.find(query, DeviceEntity.class));
    }

    @Override
    public ArrayList<DeviceEntity> findAllByUserAndToActivate(String userId) {
        return null;
    }

    @Override
    public DeviceEntity findActiveById(String deviceId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(deviceId).and("installationStatus").is(DeviceInstallationStatusEnum.ACTIVE);
        query.addCriteria(criteria);
        return (DeviceEntity) mongoTemplate.findOne(query, DeviceEntity.class);
    }

//    @Override
//    public DeviceEntity save(DeviceEntity deviceEntity) {
//        return mongoTemplate.save(deviceEntity);
//    }
//
//    @Override
//    public ArrayList<DeviceEntity> findAllByMacAddressAndNotDeactivated(String macAddress) {
//        Query query = new Query();
//        Criteria criteria = Criteria.where("networkData.macAddress").is(macAddress).and("installationStatus").ne(DeviceInstallationStatusEnum.DEACTIVATED);
//        query.addCriteria(criteria);
//        return new ArrayList<>(mongoTemplate.find(query, DeviceEntity.class));
//    }
//    @Override
//    public ArrayList<DeviceEntity> findAllByUserAndToActivate(String userId){
//        Query query = new Query();
//        Criteria criteria = Criteria.where("user._id").is(new ObjectId(userId)).and("installationStatus").is(DeviceInstallationStatusEnum.TO_ACTIVATE);
//        query.addCriteria(criteria);
//        return new ArrayList<>(mongoTemplate.find(query, DeviceEntity.class));
//    }
//
//    @Override
//    public DeviceEntity findById(String deviceId) {
//        return mongoTemplate.findById(new ObjectId(deviceId), DeviceEntity.class);
//    }
}
