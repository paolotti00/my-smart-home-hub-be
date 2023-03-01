package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.CommandEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICommandRepository extends MongoRepository<CommandEntity,String>,ICommandCustomRepository {
    Optional<CommandEntity> findCommandEntitiesByCommandId(String commandId);
}
