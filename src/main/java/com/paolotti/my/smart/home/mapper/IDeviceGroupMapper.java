package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.DeviceGroup;
import com.paolotti.my.smart.home.repository.entity.GroupDeviceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface IDeviceGroupMapper extends IBaseMapper{
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    GroupDeviceEntity toEntity(DeviceGroup deviceGroup);
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    ArrayList<DeviceGroup> toEntities (ArrayList<GroupDeviceEntity> groupDeviceEntityList);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    DeviceGroup toModel (GroupDeviceEntity groupDeviceEntity);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    ArrayList<DeviceGroup> toModels (ArrayList<GroupDeviceEntity> deviceGroupList);
}
