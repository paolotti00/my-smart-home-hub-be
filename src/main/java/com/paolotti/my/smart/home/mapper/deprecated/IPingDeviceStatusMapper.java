package com.paolotti.my.smart.home.mapper.deprecated;

import com.paolotti.my.smart.home.dto.deprecated.mqtt.PingDeviceStatusDto;
import com.paolotti.my.smart.home.mapper.IBaseMapper;
import com.paolotti.my.smart.home.model.deprecated.PingDeviceStatus;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class, IDeviceComponentMapper.class})
public interface IPingDeviceStatusMapper {
    PingDeviceStatusDto toDto (PingDeviceStatus pingDeviceStatus);
    PingDeviceStatus toModel (PingDeviceStatusDto ackDeviceStatusDto);
}
