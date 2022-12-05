package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
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
    DeviceEntity toEntity(Device device);
    ArrayList<DeviceEntity> toEntities (ArrayList<Device> deviceList);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    Device toModel (DeviceEntity deviceEntity);
    Device toModel (DeviceDto deviceDto);
    DeviceDto toDto (Device device);
    @Deprecated
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    ArrayList<Device> toModels (ArrayList<DeviceEntity> deviceList);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    ArrayList<Device> toModels (List<DeviceEntity> deviceList);
    ArrayList<DeviceDto> toDtos (ArrayList<Device>devices);
}
