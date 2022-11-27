package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.rest.IDeviceRestController;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class DeviceRestControllerImpl extends AbstractBaseRestController  implements IDeviceRestController {
    @Autowired
    IDeviceService deviceService;
    @Autowired
    IDeviceMapper deviceMapper;

    @Override
    public DeviceDto create(@RequestBody DeviceDto deviceDto) {
        // fixme pt seems that we can't create a device without group , mybe set one of default
        ResponseEntity<DeviceDto> deviceDtoResponseEntity;
        // TODO PT
        Device device = deviceMapper.toModel(deviceDto);
        device = deviceService.create(device);
        deviceDto = deviceMapper.toDto(device);

        return deviceDto;
    }
}
