package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.rest.DeviceComponentLightDto;
import com.paolotti.my.smart.home.model.DeviceComponentLight;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class,IUserMapper.class,ILedMapper.class,IDeviceComponentLightMapper.class,IDeviceComponentSensorTemperatureMapper.class})
public interface IDeviceComponentLightMapper {
    DeviceEntity.DeviceComponentEntityLight toEntity (DeviceComponentLight deviceComponentLight);
    DeviceComponentLight toModel (DeviceEntity.DeviceComponentEntityLight deviceComponentLight);
    DeviceComponentLight toModel (DeviceComponentLightDto deviceComponentLightDto);
}
