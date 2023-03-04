package com.paolotti.my.smart.home.mapper.deprecated;

import com.paolotti.my.smart.home.mapper.IBaseMapper;
import com.paolotti.my.smart.home.model.DeviceActionsSchema;
import com.paolotti.my.smart.home.repository.entity.DeviceActionSchemaEntity;
import org.mapstruct.Mapping;

import java.util.ArrayList;

public interface IActionSchemaMapper extends IBaseMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    DeviceActionSchemaEntity toEntity(DeviceActionsSchema deviceGroup);
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    ArrayList<DeviceActionSchemaEntity> toEntities (ArrayList<DeviceActionsSchema> deviceActionsSchemas);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    DeviceActionsSchema toModel (DeviceActionSchemaEntity DeviceActionSchemaEntity);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    ArrayList<DeviceActionsSchema> toModels (ArrayList<DeviceActionSchemaEntity> deviceActionSchemaEntities);
}
