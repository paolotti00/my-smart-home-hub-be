package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.deprecated.CommandEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandRepository extends MongoRepository<CommandEntity,String>, CommandRepositoryCustom {
    Optional<CommandEntity> findById(ObjectId id);
    Optional<CommandEntity> findByCommandId(String id);
}
