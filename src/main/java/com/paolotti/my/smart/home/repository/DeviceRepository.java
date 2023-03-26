package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends MongoRepository<DeviceEntity,String>, DeviceRepositoryCustom {
    Optional<DeviceEntity> findByThingId(String thingId);
    Optional<List<DeviceEntity>> findByUsersOwnersIdsContaining(String userId);
    Optional<List<DeviceEntity>> findByRoomId(String roomId);
}
