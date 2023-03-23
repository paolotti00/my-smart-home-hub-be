package com.paolotti.my.smart.home.mapper.deprecated;

import com.paolotti.my.smart.home.dto.deprecated.mqtt.DeviceStatusDto;
import com.paolotti.my.smart.home.mapper.IBaseMapper;
import com.paolotti.my.smart.home.model.deprecated.DeviceStatus;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class,IDeviceComponentMapper.class})
public interface IDeviceStatusMapper {
    DeviceStatusDto toDto (DeviceStatus deviceStatus);
    DeviceStatus toModel (DeviceStatusDto deviceStatusDto);
}
