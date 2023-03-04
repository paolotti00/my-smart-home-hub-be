package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.DeviceGroupEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceGroupRepository extends MongoRepository<DeviceGroupEntity,String>, DeviceGroupRepositoryCustom {
    Optional<DeviceGroupEntity> findById(ObjectId id);
}
