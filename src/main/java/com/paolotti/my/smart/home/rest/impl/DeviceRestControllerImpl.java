package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.mapper.IColorRgbMapper;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.rest.IDeviceRestController;
import com.paolotti.my.smart.home.rest.dto.BaseResponseDto;
import com.paolotti.my.smart.home.rest.dto.ColorRgb;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class DeviceRestControllerImpl implements IDeviceRestController {
    @Autowired
    IDeviceService deviceService;
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    IColorRgbMapper colorRgbMapper;
    private static final Logger logger = LoggerFactory.getLogger(DeviceRestControllerImpl.class);

    @Override
    public DeviceDto create(@RequestBody DeviceDto deviceDto) throws MissingFieldException {
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
    @Override
    public ResponseEntity<BaseResponseDto> switchOnAllLightsByDevice(String deviceId) throws DeviceNotExistsException, BrandNotSupportedException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device id {}", methodName, deviceId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto> responseEntity;
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            deviceService.switchAllLights(userId, deviceId, OnOffStatusEnum.ON);
            baseResponseDto.setMessage(String.format("device %s correctly switched %s", deviceId, OnOffStatusEnum.ON));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOffAllLights(String deviceId) throws DeviceNotExistsException, BrandNotSupportedException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device id {}", methodName, deviceId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto> responseEntity;
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            deviceService.switchAllLights(userId, deviceId, OnOffStatusEnum.OFF);
            baseResponseDto.setMessage(String.format("device %s correctly switched %s", deviceId, OnOffStatusEnum.OFF));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }
    @Override
    public ResponseEntity<BaseResponseDto> setColor(String deviceId, ColorRgb colorRgb) throws DeviceNotExistsException, BrandNotSupportedException, GenericException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device id {} colorRgb {}", methodName, deviceId, colorRgb);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto> responseEntity;
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            deviceService.setColor(userId, deviceId, colorRgbMapper.toModel(colorRgb));
            baseResponseDto.setMessage(String.format("device %s correctly switched %s", deviceId, OnOffStatusEnum.OFF));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }
}
