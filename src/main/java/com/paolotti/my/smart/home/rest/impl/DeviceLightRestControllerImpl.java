package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.rest.IDeviceLightRestController;
import com.paolotti.my.smart.home.rest.dto.reqres.BaseResponseDto;
import com.paolotti.my.smart.home.service.IDeviceLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class DeviceLightRestControllerImpl implements IDeviceLightRestController {
    @Autowired
    IDeviceLightService deviceLightService;
    @Override
    public ResponseEntity<BaseResponseDto> switchOnAllLightsByDevice(String deviceId) {
        String userId="ex"; // todo should be retrieved from spring Principal?
        try {
            // todo
            deviceLightService.switchOnAllLightsByDevice(userId,deviceId);
        } catch (BrandNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOffAllLightsByDevice(String deviceId) {
        String userId="ex"; // todo should be retrieved from spring Principal?
        try {
            // todo
            deviceLightService.switchOnAllLightsByDevice(userId,deviceId);
        } catch (BrandNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOnAllLightsByGroup(String groupId) {
        String userId="ex"; // todo should be retrieved from spring Principal?
        try {
            // todo
            deviceLightService.switchOnAllLightsByGroup(userId,groupId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOffAllLightsByGroup(String groupId) {
        String userId="ex"; // todo should be retrieved from spring Principal?
        try {
            // todo
            deviceLightService.switchOffAllLightsByGroup(userId,groupId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
