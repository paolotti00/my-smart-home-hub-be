package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.rest.IDeviceRestController;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class DeviceRestControllerImpl implements IDeviceRestController {
    @Autowired
    IDeviceService deviceService;
    @Autowired
    IDeviceMapper deviceMapper;
    private static final Logger logger = LoggerFactory.getLogger(DeviceRestControllerImpl.class);

    @Override
    public DeviceDto create(@RequestBody DeviceDto deviceDto) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device creation request received, deviceDto {}",methodName,deviceDto);
        ResponseEntity<DeviceDto> deviceDtoResponseEntity;
        try {
            Device device = deviceMapper.toModel(deviceDto);
            device = deviceService.create(device);
            deviceDto = deviceMapper.toDto(device);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        logger.info("{}: device correctly created, deviceDto {}",methodName,deviceDto);
        return deviceDto;
    }
}
