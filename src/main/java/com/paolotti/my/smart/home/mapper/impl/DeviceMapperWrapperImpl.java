package com.paolotti.my.smart.home.mapper.impl;

import com.paolotti.my.smart.home.mapper.IDeviceMapperBase;
import com.paolotti.my.smart.home.mapper.IDeviceMapperWrapperService;
import com.paolotti.my.smart.home.mapper.IDeviceMapperYeelight;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DeviceMapperWrapperImpl implements IDeviceMapperWrapperService {
    @Autowired
    IDeviceMapperYeelight deviceMapperYeelight;
    IDeviceMapperBase deviceMapperBase;
    @Override
    public <T extends Device> T toModel(DeviceEntity device) {
        T result;
        switch (device.getDeviceBrandEnum()){
            case YEELIGHT:
                result = (T) deviceMapperYeelight.toModel(device);
                break;
            default:
                result =null;

        }
        return result;
    }

    @Override
    public <T extends Device> DeviceEntity toEntity(T device) {
        return null;
    }

    @Override
    public <T extends Device> ArrayList<DeviceEntity> toEntities(ArrayList<T> device) {
        return null;
    }
}
