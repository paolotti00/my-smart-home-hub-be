package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.repository.DeviceRepositoryCustom;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Optional;


public class DeviceRepositoryImpl implements DeviceRepositoryCustom {
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

    @Override
    public Optional<DeviceBrandEnum> findBrandByDeviceId(String deviceId) throws DeviceNotExistsException {
        Criteria criteria = Criteria.where("id").is(deviceId);
        Optional<DeviceBrandEnum> deviceBrandEnumOptional;
        try {
            deviceBrandEnumOptional = findBrand(criteria);
        } catch (DeviceNotExistsException e) {
            throw  new DeviceNotExistsException(deviceId);
        }
        return deviceBrandEnumOptional;
    }

    @Override
    public Optional<DeviceBrandEnum> findBrandByThingId(String thingId) throws DeviceNotExistsException {

        Criteria criteria = Criteria.where("thingId").is(thingId);
        Optional<DeviceBrandEnum> deviceBrandEnumOptional;
        try {
            deviceBrandEnumOptional = findBrand(criteria);
        } catch (DeviceNotExistsException e) {
            throw  new DeviceNotExistsException("thingId " + thingId);
        }
        return deviceBrandEnumOptional;
    }

    private Optional<DeviceBrandEnum> findBrand(Criteria criteria) throws DeviceNotExistsException {
        Query query = new Query();
        query.addCriteria(criteria);
        query.fields().include("brand");
        DeviceEntity device = mongoTemplate.findOne(query, DeviceEntity.class);
        DeviceBrandEnum deviceBrandEnum = null;
        if(device!=null){
            deviceBrandEnum = device.getBrand();
        } else{
            throw new DeviceNotExistsException(null);
        }
        return Optional.ofNullable(deviceBrandEnum);
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
