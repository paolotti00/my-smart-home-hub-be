package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IDeviceRepository  extends MongoRepository<DeviceEntity,String>, IDeviceCustomRepository{
    Optional<DeviceEntity> findById(ObjectId id);
}
