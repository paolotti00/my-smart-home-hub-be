package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.DeviceGroup;
import com.paolotti.my.smart.home.repository.entity.GroupDeviceEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface IDeviceGroupMapper {
    GroupDeviceEntity toEntity(DeviceGroup deviceGroup);
    ArrayList<DeviceGroup> toEntities (ArrayList<GroupDeviceEntity> groupDeviceEntityList);
    DeviceGroup toModel (GroupDeviceEntity groupDeviceEntity);
    ArrayList<DeviceGroup> toModels (ArrayList<GroupDeviceEntity> deviceGroupList);
}
