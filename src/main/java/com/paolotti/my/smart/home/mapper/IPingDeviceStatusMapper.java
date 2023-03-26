package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.mqtt.PingDeviceStatusDto;
import com.paolotti.my.smart.home.model.PingDeviceStatus;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class, ISensorMapper.class})
public interface IPingDeviceStatusMapper {
    PingDeviceStatusDto toDto (PingDeviceStatus pingDeviceStatus);
    PingDeviceStatus toModel (PingDeviceStatusDto ackDeviceStatusDto);
}
