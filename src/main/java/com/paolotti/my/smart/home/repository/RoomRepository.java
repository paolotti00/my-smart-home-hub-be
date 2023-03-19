package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.repository.entity.RoomEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<RoomEntity,String>, RoomRepositoryCustom {
    Optional<RoomEntity> findById(ObjectId id);
    Optional<List<RoomEntity>> findByUsersOwnersIdsContaining(String userId);
}
