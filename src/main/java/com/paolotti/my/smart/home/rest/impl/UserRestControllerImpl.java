package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.dto.rest.RoomDto;
import com.paolotti.my.smart.home.dto.rest.UserDto;
import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.ResultStatusEnum;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.factory.IBeanFactoryDeviceService;
import com.paolotti.my.smart.home.interceptor.InterceptorRestControllerExceptionHandler;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.mapper.IRoomMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.Room;
import com.paolotti.my.smart.home.rest.IUserRestController;
import com.paolotti.my.smart.home.service.IDeviceService;
import com.paolotti.my.smart.home.service.IRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class UserRestControllerImpl extends InterceptorRestControllerExceptionHandler implements IUserRestController {
    @Autowired
    IRoomService roomService;
    @Autowired
    IBeanFactoryDeviceService beanFactoryService;
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    IRoomMapper roomMapper;
    @Override
    public UserDto create(UserDto userDto) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponseDto<List<RoomDto>>> getRooms(String userId) throws ValidationException  {
        List<RoomDto> roomDtoList = null;
        ResponseEntity<BaseResponseDto<List<RoomDto>>> dtoResponseEntity = null;
        BaseResponseDto<List<RoomDto>> baseResponseDto = new BaseResponseDto<>();
        try {
            List<Room> deviceList = roomService.getRoomsByUserId(userId);
            roomDtoList = roomMapper.toDtoList(deviceList);
            baseResponseDto.setData(roomDtoList);
            baseResponseDto.setResultStatus(ResultStatusEnum.OK);
            dtoResponseEntity = new ResponseEntity<BaseResponseDto<List<RoomDto>>>(baseResponseDto, HttpStatus.OK);
        } catch (ValidationException e) {
            throw e;
        }
        return dtoResponseEntity;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserRestControllerImpl.class);

    @Override
    public ResponseEntity<BaseResponseDto<List<DeviceDto>>> getDevices(String userId) throws GenericException {
        List<DeviceDto> deviceDtoList = null;
        ResponseEntity<BaseResponseDto<List<DeviceDto>>> dtoResponseEntity = null;
        BaseResponseDto<List<DeviceDto>> deviceDtoBaseResponseDto = new BaseResponseDto<>();
        try {
            IDeviceService deviceService = beanFactoryService.getDeviceServiceByBrand(DeviceBrandEnum.NO_BRAND);
            List<Device> deviceList = deviceService.getDevicesByUserId(userId);
            deviceDtoList = deviceMapper.toDtoList(deviceList);
            deviceDtoBaseResponseDto.setData(deviceDtoList);
            deviceDtoBaseResponseDto.setResultStatus(ResultStatusEnum.OK);
            dtoResponseEntity = new ResponseEntity<BaseResponseDto<List<DeviceDto>>>(deviceDtoBaseResponseDto, HttpStatus.OK);
        } catch (ValidationException e) {
           throw e;
        } catch (GenericException e) {
            throw e;
        }
        return dtoResponseEntity;
    }
}
