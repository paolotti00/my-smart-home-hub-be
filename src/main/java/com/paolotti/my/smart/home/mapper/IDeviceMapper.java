package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.enums.DeviceComponentTypeEnum;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceComponent;
import com.paolotti.my.smart.home.model.DeviceComponentLight;
import com.paolotti.my.smart.home.model.DeviceComponentSensorTemperature;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.rest.dto.DeviceComponentDto;
import com.paolotti.my.smart.home.rest.dto.DeviceComponentLightDto;
import com.paolotti.my.smart.home.rest.dto.DeviceComponentSensorTemperatureDto;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import org.bson.types.ObjectId;
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
    @Deprecated
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    ArrayList<Device> toModels (ArrayList<DeviceEntity> deviceList);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    ArrayList<Device> toModels (List<DeviceEntity> deviceList);
    ArrayList<DeviceDto> toDtos (ArrayList<Device>devices);
    DeviceComponentLightDto toDto (DeviceComponentLight deviceComponentLight);
    DeviceComponentLight toModel (DeviceComponentLightDto deviceComponentLightDto);
    DeviceComponentSensorTemperatureDto toDto (DeviceComponentSensorTemperature deviceComponentSensorTemperature);
    DeviceComponentSensorTemperature toModel (DeviceComponentSensorTemperatureDto deviceComponentSensorTemperatureDto);
    DeviceComponentLight toModel (DeviceEntity.DeviceComponentLight deviceComponentLight);
    DeviceEntity.DeviceComponentLight toEntity (DeviceComponentLight deviceComponentLight);
    DeviceComponentSensorTemperature toModel (DeviceEntity.DeviceComponentSensorTemperature deviceComponentSensorTemperature);
    DeviceEntity.DeviceComponentSensorTemperature toEntity (DeviceComponentSensorTemperature deviceComponentSensorTemperature);


    @Named("toComponentsListEntityModel")
    default ArrayList<DeviceComponent> toComponentsListEntityModel(ArrayList<DeviceEntity.DeviceComponent> deviceComponentsEntity){
        if(deviceComponentsEntity == null){
            return null ;
        }
        ArrayList<DeviceComponent> deviceComponents = new ArrayList<>();
        for(DeviceEntity.DeviceComponent deviceComponentEntity:deviceComponentsEntity){
            if(deviceComponentEntity instanceof DeviceEntity.DeviceComponentLight){
                deviceComponents.add(toModel((DeviceEntity.DeviceComponentLight)deviceComponentEntity));
            } else if(deviceComponentEntity instanceof DeviceEntity.DeviceComponentSensorTemperature){
                deviceComponents.add(toModel((DeviceEntity.DeviceComponentSensorTemperature)deviceComponentEntity));
            }
        }
        return deviceComponents;
    };
    @Named("toComponentsListModelEntity")
    default ArrayList<DeviceEntity.DeviceComponent>  toComponentsListModelEntity(ArrayList<DeviceComponent> deviceComponents){
        if(deviceComponents == null){
            return null ;
        }
        ArrayList<DeviceEntity.DeviceComponent> deviceComponentDtos = new ArrayList<>();
        for(DeviceComponent deviceComponent:deviceComponents){
            if(deviceComponent instanceof DeviceComponentLight){
                deviceComponentDtos.add(toEntity((DeviceComponentLight)deviceComponent));
            } else if(deviceComponent instanceof DeviceComponentSensorTemperature){
                deviceComponentDtos.add(toEntity((DeviceComponentSensorTemperature)deviceComponent));
            }
        }
        return deviceComponentDtos;
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
