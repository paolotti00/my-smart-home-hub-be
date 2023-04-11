package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.dto.rest.MeasurementDto;
import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.MeasurementTypeEnum;
import com.paolotti.my.smart.home.enums.ResultStatusEnum;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.RoomNotExistsException;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.factory.IBeanFactoryDeviceService;
import com.paolotti.my.smart.home.interceptor.InterceptorRestControllerExceptionHandler;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.mapper.IMeasurementMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.Measurement;
import com.paolotti.my.smart.home.rest.IRoomRestController;
import com.paolotti.my.smart.home.service.IDeviceService;
import com.paolotti.my.smart.home.service.IMeasurementService;
import com.paolotti.my.smart.home.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController()
public class RoomRestControllerImpl extends InterceptorRestControllerExceptionHandler implements IRoomRestController {
    @Autowired
    IRoomService roomService;
    @Autowired
    IMeasurementService measurementService;
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    IMeasurementMapper measurementMapper;
    @Autowired
    IBeanFactoryDeviceService beanFactoryDeviceService;
    @Override
    public ResponseEntity<BaseResponseDto<List<DeviceDto>>> getDevices(String roomId) throws GenericException {
        List<DeviceDto> dtoList = null;
        ResponseEntity<BaseResponseDto<List<DeviceDto>>> dtoResponseEntity = null;
        BaseResponseDto<List<DeviceDto>> dtoBaseResponseDto = new BaseResponseDto<>();
        try {
            IDeviceService deviceService = beanFactoryDeviceService.getDeviceServiceByBrand(DeviceBrandEnum.NO_BRAND);
            List<Device> modelList = deviceService.getDevicesByRoomId(roomId);
            dtoList = deviceMapper.toDtoList(modelList);
            dtoBaseResponseDto.setData(dtoList);
            dtoBaseResponseDto.setResultStatus(ResultStatusEnum.OK);
            dtoResponseEntity = new ResponseEntity<BaseResponseDto<List<DeviceDto>>>(dtoBaseResponseDto, HttpStatus.OK);
        } catch (GenericException e) {
            throw e;
        }
        return dtoResponseEntity;
    }

    @Override
    public ResponseEntity<BaseResponseDto<List<MeasurementDto>>> getMeasurementByRoomId(String roomId, MeasurementTypeEnum type, LocalDateTime from, LocalDateTime to) throws GenericException {
        List<MeasurementDto> dtoList = null;
        ResponseEntity<BaseResponseDto<List<MeasurementDto>>> dtoResponseEntity = null;
        BaseResponseDto<List<MeasurementDto>> dtoBaseResponseDto = new BaseResponseDto<>();
        try {
            List<Measurement> modelList = measurementService.getMeasurementByRoomIdAndDeviceId(roomId,null,type,from,to);
            dtoList = measurementMapper.toDtoList(modelList);
            dtoBaseResponseDto.setData(dtoList);
            dtoBaseResponseDto.setResultStatus(ResultStatusEnum.OK);
            dtoResponseEntity = new ResponseEntity<BaseResponseDto<List<MeasurementDto>>>(dtoBaseResponseDto, HttpStatus.OK);
        } catch (GenericException e) {
            throw e;
        }
        return dtoResponseEntity;
    }
}
