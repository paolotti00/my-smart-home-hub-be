package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends MongoRepository<DeviceEntity,String>, DeviceRepositoryCustom {
    Optional<DeviceEntity> findById(ObjectId id);
    Optional<DeviceEntity> findByThingId(String thingId);
}