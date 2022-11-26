package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.rest.IDeviceLightRestController;
import com.paolotti.my.smart.home.rest.dto.reqres.BaseResponseDto;
import com.paolotti.my.smart.home.service.IDeviceLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceLightRestControllerImpl implements IDeviceLightRestController {
    @Autowired
    IDeviceLightService deviceLightService;

    @Override
    public ResponseEntity<BaseResponseDto> switchOnAllLightsByDevice(String deviceId) {
        String userId = "ex"; // todo should be retrieved from spring Principal?
        try {
            deviceLightService.switchAllLightsByDevice(userId, deviceId, OnOffStatusEnum.ON);
        } catch (BrandNotSupportedException | DeviceNotExistsException e) {
            throw new RuntimeException(e); // todo pt abstract controller with exception handler
        }
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOffAllLightsByDevice(String deviceId) {
        String userId = "ex"; // todo should be retrieved from spring Principal?
        try {
            // todo
            deviceLightService.switchAllLightsByDevice(userId, deviceId, OnOffStatusEnum.OFF);
        } catch (BrandNotSupportedException | DeviceNotExistsException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOnAllLightsByGroup(String groupId) {
        String userId = "ex"; // todo should be retrieved from spring Principal?
        try {
            // todo
            deviceLightService.switchAllLightsByGroup(userId, groupId, OnOffStatusEnum.ON);
        } catch (Exception e) {
            throw new RuntimeException(e);// todo pt abstract controller with exception handler
        }
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOffAllLightsByGroup(String groupId) {
        String userId = "ex"; // todo should be retrieved from spring Principal?
        try {
            // todo
            deviceLightService.switchAllLightsByGroup(userId, groupId, OnOffStatusEnum.OFF);
        } catch (Exception e) {
            throw new RuntimeException(e);// todo pt abstract controller with exception handler
        }
        return null;
    }
}
