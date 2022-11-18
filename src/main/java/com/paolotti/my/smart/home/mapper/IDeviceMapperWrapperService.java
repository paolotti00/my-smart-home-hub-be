package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;

import java.util.ArrayList;

public interface IDeviceMapperWrapperService {
    <T extends Device> T toModel (DeviceEntity device);
    <T extends Device> DeviceEntity toEntity (T device);
    <T extends Device> ArrayList<DeviceEntity> toEntities (ArrayList<T> device);

    DeviceDto toDto(Device device);
}
