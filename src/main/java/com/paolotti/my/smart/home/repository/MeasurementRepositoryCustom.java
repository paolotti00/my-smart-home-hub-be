package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.enums.MeasurementTypeEnum;
import com.paolotti.my.smart.home.repository.entity.MeasurementEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MeasurementRepositoryCustom {
    Optional<List<MeasurementEntity>> findByRoomIdAndDeviceIdAndTypeAndFromAndTo(String roomId, String deviceId, MeasurementTypeEnum measurementTypeEnum, LocalDateTime from, LocalDateTime to);
}
