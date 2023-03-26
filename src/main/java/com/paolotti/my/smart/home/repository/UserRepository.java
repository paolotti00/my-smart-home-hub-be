package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity,String>, UserRepositoryCustom {
    Optional<UserEntity> findById(ObjectId id);
    Optional<UserEntity> findByEmail(String email);
}
