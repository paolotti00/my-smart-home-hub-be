package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.DeviceActionsSchema;
import com.paolotti.my.smart.home.repository.entity.DeviceActionSchemaEntity;

import java.util.ArrayList;

public interface IActionSchemaMapper {
    DeviceActionSchemaEntity toEntity(DeviceActionsSchema deviceGroup);
    ArrayList<DeviceActionSchemaEntity> toEntities (ArrayList<DeviceActionsSchema> deviceActionsSchemas);
    DeviceActionsSchema toModel (DeviceActionSchemaEntity DeviceActionSchemaEntity);
    ArrayList<DeviceActionsSchema> toModels (ArrayList<DeviceActionSchemaEntity> deviceActionSchemaEntities);
}
