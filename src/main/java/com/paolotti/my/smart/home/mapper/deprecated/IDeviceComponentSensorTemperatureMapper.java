package com.paolotti.my.smart.home.mapper.deprecated;

import com.paolotti.my.smart.home.dto.rest.DeviceComponentSensorTemperatureDto;
import com.paolotti.my.smart.home.model.DeviceComponentSensorTemperature;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;

public interface IDeviceComponentSensorTemperatureMapper {
    DeviceComponentSensorTemperature toModel(DeviceComponentSensorTemperatureDto deviceComponentSensorTemperatureDto);
    DeviceComponentSensorTemperature toModel(DeviceEntity.DeviceComponentEntitySensorTemperature deviceComponentSensorTemperature);
    DeviceEntity.DeviceComponentEntitySensorTemperature toEntity(DeviceComponentSensorTemperature deviceComponentSensorTemperature);
    DeviceComponentSensorTemperatureDto toDto(DeviceComponentSensorTemperature deviceComponentSensorTemperature);
}
