package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.enums.DeviceCreationResultStatusEnum;
import com.paolotti.my.smart.home.exception.DeviceAlreadyRegisteredException;
import com.paolotti.my.smart.home.exception.DeviceCreationException;
import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationRequestDto;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationResponseDto;
import com.paolotti.my.smart.home.service.IDeviceService;
import com.paolotti.my.smart.home.service.impl.DeviceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("device")
public class DeviceRestController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceRestController.class);
    @Autowired
    IDeviceService deviceService;

    @PostMapping("register")
    ResponseEntity<DeviceRegistrationResponseDto> handleDeviceRegistrationRequest(@RequestBody  DeviceRegistrationRequestDto registrationRequestDto) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device auto register flow received request dto, deviceRegistrationRequestDto {}",methodName,registrationRequestDto);
        ResponseEntity<DeviceRegistrationResponseDto> registrationResponseDtoResponseEntity;
        DeviceRegistrationResponseDto deviceRegistrationResponseDto = new DeviceRegistrationResponseDto();
        try {
            deviceRegistrationResponseDto = deviceService.deviceSelfRegisteringHandling(registrationRequestDto);
            deviceRegistrationResponseDto.setCreationResult(DeviceCreationResultStatusEnum.CREATED);
            registrationResponseDtoResponseEntity = new ResponseEntity<>(deviceRegistrationResponseDto, HttpStatus.OK);
        } catch (MissingFieldException e) {
            deviceRegistrationResponseDto.setCreationResult(DeviceCreationResultStatusEnum.FAILED);
            registrationResponseDtoResponseEntity = new ResponseEntity<>(deviceRegistrationResponseDto, HttpStatus.BAD_REQUEST);
            logger.info("{}: device auto register flow error: {}",methodName,e.getMessage());
            e.printStackTrace();
        } catch (DeviceAlreadyRegisteredException | DeviceCreationException e) {
            deviceRegistrationResponseDto.setCreationResult(DeviceCreationResultStatusEnum.FAILED);
            registrationResponseDtoResponseEntity = new ResponseEntity<>(deviceRegistrationResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
            logger.info("{}: device auto register flow error: {}",methodName,e.getMessage());
            e.printStackTrace();
        }
        logger.info("{}: device auto register flow finished  deviceRegistrationResponseDto {}",methodName,deviceRegistrationResponseDto);
        return registrationResponseDtoResponseEntity;
    };
}
