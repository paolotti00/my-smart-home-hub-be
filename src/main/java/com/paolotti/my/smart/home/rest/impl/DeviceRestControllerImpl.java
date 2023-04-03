package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.dto.ExtraActionCommandDataDto;
import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.enums.ResultStatusEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.factory.IBeanFactoryDeviceService;
import com.paolotti.my.smart.home.interceptor.InterceptorRestControllerExceptionHandler;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.mapper.IExtraActionCommandDataMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.ExtraActionCommandData;
import com.paolotti.my.smart.home.rest.IDeviceRestController;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class DeviceRestControllerImpl extends InterceptorRestControllerExceptionHandler implements IDeviceRestController {
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    IBeanFactoryDeviceService beanFactoryDeviceService;
    @Autowired
    IExtraActionCommandDataMapper extraActionCommandDataMapper;

    private static final Logger logger = LoggerFactory.getLogger(DeviceRestControllerImpl.class);

    @Override
    public DeviceDto create(@RequestBody DeviceDto deviceDto) throws GenericException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device creation request received, deviceDto {}",methodName,deviceDto);
        ResponseEntity<DeviceDto> deviceDtoResponseEntity;
        try {
            Device device = deviceMapper.toModel(deviceDto);
            IDeviceService deviceService = beanFactoryDeviceService.getDeviceServiceByBrand(device.getBrand());
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
    public ResponseEntity<BaseResponseDto<DeviceDto>> getDevice(String deviceId) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device retrieve request received, deviceId {}",methodName,deviceId);
        ResponseEntity<BaseResponseDto<DeviceDto>> deviceDtoResponseEntity = null;
        BaseResponseDto<DeviceDto> deviceDtoBaseResponseDto= new BaseResponseDto<>();
        DeviceDto deviceDto = null;
        try {
            deviceDtoBaseResponseDto.setResultStatus(ResultStatusEnum.OK);
            IDeviceService deviceService = beanFactoryDeviceService.getDeviceServiceById(deviceId);
            Device device = deviceService.getDevice(deviceId);
            deviceDto = deviceMapper.toDto(device);
            deviceDtoBaseResponseDto.setData(deviceDto);
            deviceDtoResponseEntity = new ResponseEntity<BaseResponseDto<DeviceDto>>(deviceDtoBaseResponseDto,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("{}: device correctly retrieved, deviceDto {}",methodName,deviceDto);
        return deviceDtoResponseEntity;
    }

    @Override
    public ResponseEntity<BaseResponseDto<?>> switchLights(String deviceId, OnOffStatusEnum onOffStatus) throws GenericException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device id {}", methodName, deviceId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto<?>> responseEntity;
        BaseResponseDto<?> baseResponseDto = new BaseResponseDto<>();
        try {
            IDeviceService deviceService = beanFactoryDeviceService.getDeviceServiceById(deviceId);
            deviceService.switchAllLights(userId, deviceId, null, onOffStatus, CommandDestinationTypeEnum.TO_DEVICE);
            baseResponseDto.setMessage(String.format("device %s correctly switched %s", deviceId, OnOffStatusEnum.OFF));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }
    @Override
    public ResponseEntity<BaseResponseDto<?>> setLightColor(String deviceId, List<Integer> colorRgbAndIntensity) throws DeviceNotExistsException, BrandNotSupportedException, GenericException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device id {}", methodName, deviceId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto<?>> responseEntity;
        BaseResponseDto<?> baseResponseDto = new BaseResponseDto<>();
        try {
            IDeviceService deviceService = beanFactoryDeviceService.getDeviceServiceById(deviceId);
            deviceService.setLightColor(userId, deviceId,null, colorRgbAndIntensity, CommandDestinationTypeEnum.TO_DEVICE);
            baseResponseDto.setMessage(String.format("device %s correctly switched %s", deviceId, OnOffStatusEnum.OFF));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }
    @Override
    public ResponseEntity<BaseResponseDto<List<ExtraActionCommandDataDto>>> getSupportedActions(String deviceId) throws DeviceNotExistsException,GenericException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: getting supported ExtraActions for device id {}", methodName, deviceId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto<List<ExtraActionCommandDataDto>>> responseEntity;
        BaseResponseDto<List<ExtraActionCommandDataDto>> baseResponseDto = new BaseResponseDto<>();
        List<ExtraActionCommandDataDto> extraActionCommandDataDtoList;
        try {
            baseResponseDto.setResultStatus(ResultStatusEnum.OK);
            IDeviceService deviceService = beanFactoryDeviceService.getDeviceServiceById(deviceId);
            List<ExtraActionCommandData> extraActionCommandDataList = deviceService.getSupportedExtraActions(deviceId);
            extraActionCommandDataDtoList = extraActionCommandDataMapper.toDtoList(extraActionCommandDataList);
            baseResponseDto.setData(extraActionCommandDataDtoList);
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }
    @Override
    public ResponseEntity<BaseResponseDto<?>> doAction(String deviceId, ExtraActionCommandDataDto extraActionCommandDataDto) throws DeviceNotExistsException, BrandNotSupportedException, GenericException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device id {} extraActionCommandDataDto {}", methodName, deviceId, extraActionCommandDataDto);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto<?>> responseEntity;
        BaseResponseDto<?> baseResponseDto = new BaseResponseDto<>();
        try {
            IDeviceService deviceService = beanFactoryDeviceService.getDeviceServiceById(deviceId);
            deviceService.doExtraAction(userId, deviceId,null, extraActionCommandDataMapper.toModel(extraActionCommandDataDto), CommandDestinationTypeEnum.TO_DEVICE);
            baseResponseDto.setMessage(String.format("device %s correctly sent %s", deviceId, extraActionCommandDataDto));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }
}
