package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.ResultStatusEnum;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.RoomNotExistsException;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.factory.IBeanFactoryDeviceService;
import com.paolotti.my.smart.home.interceptor.InterceptorRestControllerExceptionHandler;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.rest.IRoomRestController;
import com.paolotti.my.smart.home.service.IDeviceService;
import com.paolotti.my.smart.home.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class RoomRestControllerImpl extends InterceptorRestControllerExceptionHandler implements IRoomRestController {
    @Autowired
    IRoomService roomService;
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    IBeanFactoryDeviceService beanFactoryService;
    @Override
    public ResponseEntity<BaseResponseDto<List<DeviceDto>>> getDevices(String roomId) throws GenericException {
        List<DeviceDto> deviceDtoList = null;
        ResponseEntity<BaseResponseDto<List<DeviceDto>>> dtoResponseEntity = null;
        BaseResponseDto<List<DeviceDto>> deviceDtoBaseResponseDto = new BaseResponseDto<>();
        try {
            IDeviceService deviceService = beanFactoryService.getDeviceServiceByBrand(DeviceBrandEnum.NO_BRAND);
            List<Device> deviceList = deviceService.getDevicesByRoomId(roomId);
            deviceDtoList = deviceMapper.toDtoList(deviceList);
            deviceDtoBaseResponseDto.setData(deviceDtoList);
            deviceDtoBaseResponseDto.setResultStatus(ResultStatusEnum.OK);
            dtoResponseEntity = new ResponseEntity<BaseResponseDto<List<DeviceDto>>>(deviceDtoBaseResponseDto, HttpStatus.OK);
        } catch (ValidationException | RoomNotExistsException e) {
            throw e;
        } catch (GenericException e) {
            throw e;
        }
        return dtoResponseEntity;
    }
}
