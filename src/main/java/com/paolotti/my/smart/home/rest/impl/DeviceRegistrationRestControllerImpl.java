package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.constant.RestConst;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.mapper.deprecated.IDeviceMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.rest.IDeviceRegistrationRestController;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.service.IRegistrationDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class DeviceRegistrationRestControllerImpl implements IDeviceRegistrationRestController {
    @Autowired
    IRegistrationDeviceService deviceService;
    @Autowired
    IDeviceMapper deviceMapper;

    private static final Logger logger = LoggerFactory.getLogger(DeviceRegistrationRestControllerImpl.class);

    @Override
    public ResponseEntity<DeviceDto> handleDeviceRegistrationRequest(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @RequestBody DeviceDto deviceDto) throws DeviceAlreadyRegisteredException, MissingFieldException, DeviceCreationException, UserNotExistException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device auto register flow received request dto, deviceRegistrationRequestDto {}",methodName,deviceDto);
        ResponseEntity<DeviceDto> deviceDtoResponseEntity;
        try {
            // converting to model
            Device device =  deviceMapper.toModel(deviceDto);
            // calling service
            device = deviceService.deviceSelfRegisteringHandling(userId, device);
            // converting to dto
            deviceDto = deviceMapper.toDto(device);
            deviceDtoResponseEntity = new ResponseEntity<>(deviceDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{}: device auto register flow error",methodName);
            throw e;
        }
        logger.info("{}: device auto register flow finished  deviceRegistrationResponseDto {}",methodName,deviceDto);
        return deviceDtoResponseEntity;
    };
    @Override
    public ResponseEntity<List<DeviceDto>> getDevicesToActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId) throws MissingFieldException, UserNotExistException {
        // todo get the user from the user that is logged in
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: received request , userId {}",methodName,userId);
        ResponseEntity<List<DeviceDto>> responseEntity;
        DeviceDto deviceDto = new DeviceDto();
        List<DeviceDto> deviceDtosList = new ArrayList<>();
        try {
            ArrayList<Device> devices = deviceService.getDeviceToActivate(userId);
            deviceDtosList = deviceMapper.toDtoList(devices);
            responseEntity = new ResponseEntity<>(deviceDtosList,HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{}: userId {} error {}",methodName,userId,e.getMessage());
            throw e;
        }
        logger.info("{}: userId {} result : {} ",methodName,userId,deviceDtosList);
        return responseEntity;
    }
    @Override
    public ResponseEntity<DeviceDto> deviceActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @PathVariable String deviceId) throws DeviceWrongStatusException, DeviceNotExistsException, MissingFieldException, DeviceAlreadyActivated, UserNotExistException {
        // todo get the user from the user that is logged in
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: received request to activate device with id {}, userId {}",methodName,deviceId,userId);
        ResponseEntity<DeviceDto> responseEntity;
        DeviceDto deviceDto = new DeviceDto();
        try {
            Device device = deviceService.activate(userId, deviceId);
            // convert to dto
            deviceDto = deviceMapper.toDto(device);
            responseEntity = new ResponseEntity<>(deviceDto,HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{}: activate device with id  {} error {}",methodName,deviceId,e.getMessage());
            throw e;
        }
        logger.info("{}:request to activate device with id {}, userId {} done, deviceDtoResponseEntity {}",methodName,deviceId,userId,responseEntity);
        return responseEntity;

    }
}
