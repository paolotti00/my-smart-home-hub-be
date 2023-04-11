package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.MeasurementEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends MongoRepository<MeasurementEntity,String>,MeasurementRepositoryCustom {

}

