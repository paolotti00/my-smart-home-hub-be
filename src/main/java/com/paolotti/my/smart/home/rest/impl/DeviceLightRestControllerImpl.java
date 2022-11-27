package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GroupNotExistsException;
import com.paolotti.my.smart.home.rest.IDeviceLightRestController;
import com.paolotti.my.smart.home.rest.dto.reqres.BaseResponseDto;
import com.paolotti.my.smart.home.service.IDeviceLightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceLightRestControllerImpl implements IDeviceLightRestController {
    @Autowired
    IDeviceLightService deviceLightService;
    private static final Logger logger = LoggerFactory.getLogger(DeviceLightRestControllerImpl.class);

    @Override
    public ResponseEntity<BaseResponseDto> switchOnAllLightsByDevice(String deviceId) throws DeviceNotExistsException, BrandNotSupportedException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device id {}", methodName, deviceId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto> responseEntity;
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            deviceLightService.switchAllLightsByDevice(userId, deviceId, OnOffStatusEnum.ON);
            baseResponseDto.setMessage(String.format("device %s correctly switched %s", deviceId, OnOffStatusEnum.ON));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOffAllLightsByDevice(String deviceId) throws DeviceNotExistsException, BrandNotSupportedException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device id {}", methodName, deviceId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto> responseEntity;
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            deviceLightService.switchAllLightsByDevice(userId, deviceId, OnOffStatusEnum.OFF);
            baseResponseDto.setMessage(String.format("device %s correctly switched %s", deviceId, OnOffStatusEnum.OFF));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOnAllLightsByGroup(String groupId) throws GroupNotExistsException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: group id {}", methodName, groupId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto> responseEntity;
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            deviceLightService.switchAllLightsByGroup(userId, groupId, OnOffStatusEnum.ON);
            baseResponseDto.setMessage(String.format("devices of group %s correctly switched %s", groupId, OnOffStatusEnum.ON));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOffAllLightsByGroup(String groupId) throws GroupNotExistsException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: group id {}", methodName, groupId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto> responseEntity;
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            deviceLightService.switchAllLightsByGroup(userId, groupId, OnOffStatusEnum.OFF);
            baseResponseDto.setMessage(String.format("devices of group %s correctly switched %s", groupId, OnOffStatusEnum.OFF));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }
}
