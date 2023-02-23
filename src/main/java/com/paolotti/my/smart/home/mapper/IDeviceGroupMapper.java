package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.DeviceGroup;
import com.paolotti.my.smart.home.repository.entity.DeviceGroupEntity;
import org.bson.types.ObjectId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class,IDeviceMapper.class})
public interface IDeviceGroupMapper{
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    DeviceGroupEntity toEntity(DeviceGroup deviceGroup);

    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    ArrayList<DeviceGroupEntity> toEntities (ArrayList<DeviceGroup> deviceGroupEntityList);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    DeviceGroup toModel (DeviceGroupEntity deviceGroupEntity);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    ArrayList<DeviceGroup> toModels (ArrayList<DeviceGroupEntity> deviceGroupEntityList);
}
