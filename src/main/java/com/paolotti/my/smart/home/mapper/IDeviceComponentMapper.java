package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.DeviceComponentDto;
import com.paolotti.my.smart.home.dto.rest.DeviceComponentLightDto;
import com.paolotti.my.smart.home.model.DeviceComponent;
import com.paolotti.my.smart.home.model.DeviceComponentLight;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.awt.*;
import java.util.ArrayList;

@Mapper( componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class})
public interface IDeviceComponentMapper {
    default DeviceComponent toModel(DeviceComponentDto deviceComponentDto){
        return getMapper(deviceComponentDto);
    };
    default ArrayList<DeviceComponent> toModelList (ArrayList<DeviceComponentDto> deviceComponentDtoList){
        ArrayList<DeviceComponent> deviceComponentsList = new ArrayList<>();
        for(DeviceComponentDto deviceComponentDto:deviceComponentDtoList){
            deviceComponentsList.add(getMapper(deviceComponentDto));
        }
        return deviceComponentsList;
    }

    // light component
    DeviceEntity.DeviceComponentEntityLight toEntity (DeviceComponentLight deviceComponentLight);
    DeviceComponentLight toModel (DeviceEntity.DeviceComponentEntityLight deviceComponentLight);
    DeviceComponentLight toModel (DeviceComponentLightDto deviceComponentLightDto);

    default DeviceComponent getMapper(DeviceComponentDto deviceComponentDto){
        if(deviceComponentDto instanceof DeviceComponentLightDto){
            return toModel((DeviceComponentLightDto) deviceComponentDto);
        } else {
            return null;
        }
    }

}
