package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.DeviceComponentDto;
import com.paolotti.my.smart.home.dto.rest.DeviceComponentLightDto;
import com.paolotti.my.smart.home.model.DeviceComponent;
import com.paolotti.my.smart.home.model.DeviceComponentLight;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@Mapper( componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class})
public interface IDeviceComponentMapper {
    Logger logger = LoggerFactory.getLogger(IDeviceComponentMapper.class);
    default DeviceComponent toModel(DeviceComponentDto deviceComponentDto){
        return getMapperToModel(deviceComponentDto);
    };
    default DeviceComponentDto toDto (DeviceComponent deviceComponent){
        return getMapperToDto(deviceComponent);
    }
    default ArrayList<DeviceComponent> toModelList (ArrayList<DeviceComponentDto> deviceComponentDtoList){
        ArrayList<DeviceComponent> deviceComponentsList = new ArrayList<>();
        for(DeviceComponentDto deviceComponentDto:deviceComponentDtoList){
            deviceComponentsList.add(getMapperToModel(deviceComponentDto));
        }
        return deviceComponentsList;
    }
    default ArrayList<DeviceComponentDto>toDtoList(ArrayList<DeviceComponent> deviceComponents){
        ArrayList<DeviceComponentDto> deviceComponentDtoList = new ArrayList<>();
        for(DeviceComponent deviceComponent:deviceComponents){
            deviceComponentDtoList.add(getMapperToDto(deviceComponent));
        }
        return deviceComponentDtoList;
    }

    // light component
    DeviceEntity.DeviceComponentEntityLight toEntity (DeviceComponentLight deviceComponentLight);
    DeviceComponentLight toModel (DeviceEntity.DeviceComponentEntityLight deviceComponentLight);
    DeviceComponentLight toModel (DeviceComponentLightDto deviceComponentLightDto);
    DeviceComponentLightDto toDto (DeviceComponentLight deviceComponentLight);

    default DeviceComponent getMapperToModel(DeviceComponentDto deviceComponentDto){
        if(deviceComponentDto instanceof DeviceComponentLightDto){
            return toModel((DeviceComponentLightDto) deviceComponentDto);
        } else {
            logger.warn("the class {} is not supported so the component won't be well mapped, you have to had it here.",deviceComponentDto.getClass());
            return null;
        }
    }
    default DeviceComponentDto getMapperToDto(DeviceComponent deviceComponent){
        if(deviceComponent instanceof DeviceComponentLight){
            return toDto((DeviceComponentLight) deviceComponent);
        } else {
            logger.warn("the class {} is not supported so the component won't be well mapped , you have to had it here.",deviceComponent.getClass());
            return null;
        }
    }

}
