package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class, IUserMapper.class})
public interface IDeviceMapper {
    DeviceDto toDto (Device model);
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    DeviceEntity toEntity(Device model);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    Device toModel (DeviceEntity entity);
    Device toModel (DeviceDto dto);
    ArrayList<DeviceEntity> toEntities (ArrayList<Device> modelList);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    List<Device> toModelList(List<DeviceEntity> entityList);
    List<DeviceDto> toDtoList(List<Device> modelList);


}
