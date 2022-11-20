package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.DeviceGroup;
import com.paolotti.my.smart.home.repository.entity.DeviceGroupEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface IDeviceGroupMapper {
    DeviceGroupEntity toEntity(DeviceGroup deviceGroup);
    ArrayList<DeviceGroup> toEntities (ArrayList<DeviceGroupEntity> deviceGroupEntityList);
    DeviceGroup toModel (DeviceGroupEntity deviceGroupEntity);
    ArrayList<DeviceGroup> toModels (ArrayList<DeviceGroupEntity> deviceGroupList);
}
