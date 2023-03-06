package com.paolotti.my.smart.home.mapper.deprecated;

import com.paolotti.my.smart.home.mapper.IBaseMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceComponent;
import com.paolotti.my.smart.home.model.DeviceComponentLight;
import com.paolotti.my.smart.home.model.DeviceComponentSensorTemperature;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.dto.DeviceComponentDto;
import com.paolotti.my.smart.home.dto.rest.DeviceComponentLightDto;
import com.paolotti.my.smart.home.dto.rest.DeviceComponentSensorTemperatureDto;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class,IUserMapper.class})
public interface IDeviceMapper {
    Logger logger = LoggerFactory.getLogger(IDeviceMapper.class);

    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    @Mapping(source = "components", target = "components", qualifiedByName = "toComponentsListModelEntity")
    DeviceEntity toEntity(Device device);
    ArrayList<DeviceEntity> toEntities (ArrayList<Device> deviceList);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    @Mapping(source = "components", target = "components", qualifiedByName = "toComponentsListEntityModel")
    Device toModel (DeviceEntity deviceEntity);
    @Mapping(source = "components", target = "components", qualifiedByName = "toComponentsListDtoModel")
    Device toModel (DeviceDto deviceDto);
    @Mapping(source = "components", target = "components", qualifiedByName = "toComponentsListModelDto")
    DeviceDto toDto (Device device);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    List<Device> toModelList(List<DeviceEntity> deviceList);
    List<DeviceDto> toDtoList(List<Device>devices);
    DeviceComponentLightDto toDto (DeviceComponentLight deviceComponentLight);
    DeviceComponentLight toModel (DeviceComponentLightDto deviceComponentLightDto);
    DeviceComponentSensorTemperatureDto toDto (DeviceComponentSensorTemperature deviceComponentSensorTemperature);
    DeviceComponentSensorTemperature toModel (DeviceComponentSensorTemperatureDto deviceComponentSensorTemperatureDto);
    DeviceComponentLight toModel (DeviceEntity.DeviceComponentEntityLight deviceComponentLight);
    DeviceEntity.DeviceComponentEntityLight toEntity (DeviceComponentLight deviceComponentLight);
    DeviceComponentSensorTemperature toModel (DeviceEntity.DeviceComponentEntitySensorTemperature deviceComponentSensorTemperature);
    DeviceEntity.DeviceComponentEntitySensorTemperature toEntity (DeviceComponentSensorTemperature deviceComponentSensorTemperature);


    @Named("toComponentsListEntityModel")
    default ArrayList<DeviceComponent> toComponentsListEntityModel(ArrayList<DeviceEntity.DeviceComponentEntity> deviceComponentsEntityEntity){
        if(deviceComponentsEntityEntity == null){
            return null ;
        }
        ArrayList<DeviceComponent> deviceComponents = new ArrayList<>();
        for(DeviceEntity.DeviceComponentEntity deviceComponentEntity: deviceComponentsEntityEntity){
            if(deviceComponentEntity instanceof DeviceEntity.DeviceComponentEntityLight){
                deviceComponents.add(toModel((DeviceEntity.DeviceComponentEntityLight)deviceComponentEntity));
            } else if(deviceComponentEntity instanceof DeviceEntity.DeviceComponentEntitySensorTemperature){
                deviceComponents.add(toModel((DeviceEntity.DeviceComponentEntitySensorTemperature)deviceComponentEntity));
            }
        }
        return deviceComponents;
    };
    @Named("toComponentsListModelEntity")
    default ArrayList<DeviceEntity.DeviceComponentEntity>  toComponentsListModelEntity(ArrayList<DeviceComponent> deviceComponents){
        if(deviceComponents == null){
            return null ;
        }
        ArrayList<DeviceEntity.DeviceComponentEntity> deviceComponentEntityDtos = new ArrayList<>();
        for(DeviceComponent deviceComponent:deviceComponents){
            if(deviceComponent instanceof DeviceComponentLight){
                deviceComponentEntityDtos.add(toEntity((DeviceComponentLight)deviceComponent));
            } else if(deviceComponent instanceof DeviceComponentSensorTemperature){
                deviceComponentEntityDtos.add(toEntity((DeviceComponentSensorTemperature)deviceComponent));
            }
        }
        return deviceComponentEntityDtos;
    };
    @Named("toComponentsListDtoModel")
    default ArrayList<DeviceComponent> toComponentsListDtoModel(ArrayList<DeviceComponentDto> deviceComponentDtos){
        if(deviceComponentDtos == null){
            return null ;
        }
        ArrayList<DeviceComponent> deviceComponents = new ArrayList<>();
        for(DeviceComponentDto deviceComponentDto:deviceComponentDtos){
            if(deviceComponentDto instanceof DeviceComponentLightDto){
                deviceComponents.add(toModel((DeviceComponentLightDto)deviceComponentDto));
            } else if(deviceComponentDto instanceof DeviceComponentSensorTemperatureDto){
                deviceComponents.add(toModel((DeviceComponentSensorTemperatureDto)deviceComponentDto));
            }
        }
        return deviceComponents;
    }
    @Named("toComponentsListModelDto")
    default ArrayList<DeviceComponentDto> toComponentsListModelDto(ArrayList<DeviceComponent> deviceComponents){
        if(deviceComponents == null){
            return null ;
        }
        ArrayList<DeviceComponentDto> deviceComponentDtos = new ArrayList<>();
        for(DeviceComponent deviceComponent:deviceComponents){
            if(deviceComponent instanceof DeviceComponentLight){
                deviceComponentDtos.add(toDto((DeviceComponentLight)deviceComponent));
            } else if(deviceComponent instanceof DeviceComponentSensorTemperature){
                deviceComponentDtos.add(toDto((DeviceComponentSensorTemperature)deviceComponent));
            }
        }
        return deviceComponentDtos;
    }
}
