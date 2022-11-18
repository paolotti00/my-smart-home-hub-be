package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.DeviceBrandYeelight;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface  IDeviceMapperYeelight {
    DeviceBrandYeelight toModel(DeviceEntity device);
    DeviceEntity toEntity(DeviceBrandYeelight device);
    ArrayList<DeviceBrandYeelight> toModels(ArrayList<DeviceEntity> device);
}
