package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.rest.IDeviceLightRestController;
import com.paolotti.my.smart.home.rest.dto.reqres.BaseResponseDto;
import org.springframework.http.ResponseEntity;

public class DeviceLightRestControllerImpl implements IDeviceLightRestController {
    @Override
    public ResponseEntity<BaseResponseDto> switchOnAllLightsByDevice(String deviceId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOffAllLightsByDevice(String deviceId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOnAllLightsByGroup(String groupId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOffAllLightsByGroup(String groupId) {
        return null;
    }
}
