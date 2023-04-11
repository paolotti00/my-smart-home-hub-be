package com.paolotti.my.smart.home.repository.impl;

import com.paolotti.my.smart.home.enums.MeasurementTypeEnum;
import com.paolotti.my.smart.home.repository.MeasurementRepositoryCustom;
import com.paolotti.my.smart.home.repository.entity.MeasurementEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MeasurementRepositoryImpl implements MeasurementRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Optional<List<MeasurementEntity>> findByRoomIdAndDeviceIdAndTypeAndFromAndTo(String roomId, String deviceId, MeasurementTypeEnum measurementTypeEnum, LocalDateTime from, LocalDateTime to) {
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (roomId != null) {
            criteria = criteria.and("roomId").is(roomId);
        }

        if (deviceId != null) {
            criteria = criteria.and("deviceId").is(deviceId);
        }

        if(measurementTypeEnum!=null){
            criteria = criteria.and("type").is(measurementTypeEnum);
        }

        if (from != null && to != null) {
            criteria = criteria.andOperator(
                    Criteria.where("date").gte(from),
                    Criteria.where("date").lte(to)
            );
        } else if (from != null) {
            criteria = criteria.and("date").gte(from);
        } else if (to != null) {
            criteria = criteria.and("date").lte(to);
        }

        query.addCriteria(criteria);

        List<MeasurementEntity> measurements = mongoTemplate.find(query, MeasurementEntity.class);
        return Optional.ofNullable(measurements.isEmpty() ? null : measurements);
    }
}
