package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.interceptor.InterceptorRestControllerExceptionHandler;
import com.paolotti.my.smart.home.rest.IRoomRestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RoomRestControllerImpl extends InterceptorRestControllerExceptionHandler implements IRoomRestController {
    @Override
    public ResponseEntity<BaseResponseDto<List<DeviceDto>>> getDevices(String roomId) throws ValidationException {
        return null;
    }
}
