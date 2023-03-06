package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.enums.ResultStatusEnum;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.interceptor.InterceptorRestControllerExceptionHandler;
import com.paolotti.my.smart.home.mapper.deprecated.IDeviceMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.rest.IUserRestController;
import com.paolotti.my.smart.home.dto.rest.UserDto;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class UserRestControllerImplRestControllerExceptionHandler extends InterceptorRestControllerExceptionHandler implements IUserRestController {
    @Autowired
    IDeviceService deviceService;
    @Autowired
    IDeviceMapper deviceMapper;
    @Override
    public UserDto create(UserDto userDto) {
        return null;
    }
    private static final Logger logger = LoggerFactory.getLogger(UserRestControllerImplRestControllerExceptionHandler.class);

    @Override
    public ResponseEntity<BaseResponseDto<List<DeviceDto>>> getDevices(String userId) throws ValidationException {
        List<DeviceDto> deviceDtoList = null;
        ResponseEntity<BaseResponseDto<List<DeviceDto>>> dtoResponseEntity = null;
        BaseResponseDto<List<DeviceDto>> deviceDtoBaseResponseDto = new BaseResponseDto<>();
        try {
            List<Device> deviceList = deviceService.getDevicesByUserId(userId);
            deviceDtoList = deviceMapper.toDtoList(deviceList);
            deviceDtoBaseResponseDto.setData(deviceDtoList);
            deviceDtoBaseResponseDto.setResultStatus(ResultStatusEnum.OK);
            dtoResponseEntity = new ResponseEntity<BaseResponseDto<List<DeviceDto>>>(deviceDtoBaseResponseDto, HttpStatus.OK);
        } catch (ValidationException e) {
           throw e;
        }
        return dtoResponseEntity;
    }
}
