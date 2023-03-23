package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.mapper.deprecated.IUserMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class, IUserMapper.class})
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
}
