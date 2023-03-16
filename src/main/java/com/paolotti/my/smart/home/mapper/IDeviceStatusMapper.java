package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.mqtt.DeviceStatusDto;
import com.paolotti.my.smart.home.model.DeviceStatus;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class})
public interface IDeviceStatusMapper {
    DeviceStatusDto toDto (DeviceStatus deviceStatus);
    DeviceStatus toModel (DeviceStatusDto deviceStatusDto);
}
