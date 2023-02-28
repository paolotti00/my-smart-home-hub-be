package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.rest.DeviceComponentLightDto;
import com.paolotti.my.smart.home.model.DeviceComponentLight;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class,IUserMapper.class,ILedMapper.class,IDeviceComponentLightMapper.class,IDeviceComponentSensorTemperatureMapper.class})
public interface IDeviceComponentLightMapper {
    DeviceEntity.DeviceComponentLight toEntity (DeviceComponentLight deviceComponentLight);
    DeviceComponentLight toModel (DeviceEntity.DeviceComponentLight deviceComponentLight);
    DeviceComponentLight toModel (DeviceComponentLightDto deviceComponentLightDto);
}
