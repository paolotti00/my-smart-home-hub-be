package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.constant.MessageConst;
import com.paolotti.my.smart.home.constant.RestConst;
import com.paolotti.my.smart.home.enums.ResultStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.mapper.IDeviceRegistrationMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceRegistrationRequest;
import com.paolotti.my.smart.home.rest.IDeviceRegistrationRestController;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import com.paolotti.my.smart.home.rest.dto.reqres.*;
import com.paolotti.my.smart.home.service.IRegistrationDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController()
public class DeviceRegistrationRestController extends AbstractBaseRestController  implements IDeviceRegistrationRestController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceRegistrationRestController.class);
    @Autowired
    IRegistrationDeviceService deviceService;
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    IDeviceRegistrationMapper deviceRegistrationMapper;

    @Override
    public ResponseEntity<DeviceDto> handleDeviceRegistrationRequest(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @RequestBody DeviceRegistrationRequestDto registrationRequestDto) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device auto register flow received request dto, deviceRegistrationRequestDto {}",methodName,registrationRequestDto);
        ResponseEntity<DeviceDto> deviceDtoResponseEntity;
        DeviceDto deviceDto = new DeviceDto();
        BaseResponseDetailDto responseDtoDetail = new BaseResponseDetailDto();
        try {
            // converting to model
            DeviceRegistrationRequest deviceRegistrationRequest =  deviceRegistrationMapper.toDeviceRegistrationRequest(registrationRequestDto);
            // calling service
            Device device = deviceService.deviceSelfRegisteringHandling(userId, deviceRegistrationRequest);
            // converting to dto
            deviceDto = deviceMapper.toDto(device);
            responseDtoDetail.setResultStatus(ResultStatusEnum.SUCCESS);
            deviceDto.setResponseDetail(responseDtoDetail);
            deviceDtoResponseEntity = new ResponseEntity<>(deviceDto, HttpStatus.OK);
        } catch (MissingFieldException e) {
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(e.getMessage());
            deviceDto.setResponseDetail(responseDtoDetail);
            deviceDtoResponseEntity = new ResponseEntity<>(deviceDto, HttpStatus.BAD_REQUEST);
            logger.info("{}: device auto register flow error: {}",methodName,e.getMessage());
            e.printStackTrace();
        } catch (DeviceAlreadyRegisteredException | DeviceCreationException e) {
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(e.getMessage());
            deviceDto.setResponseDetail(responseDtoDetail);
            deviceDtoResponseEntity = new ResponseEntity<>(deviceDto, HttpStatus.INTERNAL_SERVER_ERROR);
            logger.info("{}: device auto register flow error: {}",methodName,e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(MessageConst.GENERIC_ERROR);
            deviceDto.setResponseDetail(responseDtoDetail);
            deviceDtoResponseEntity = new ResponseEntity<>(deviceDto, HttpStatus.INTERNAL_SERVER_ERROR);
            logger.info("{}: device auto register flow error: {}",methodName,e.getMessage());
            e.printStackTrace();
        }
        logger.info("{}: device auto register flow finished  deviceRegistrationResponseDto {}",methodName,deviceDto);
        return deviceDtoResponseEntity;
    };
    @Override
    public ResponseEntity<DeviceListResponseDto> getDevicesToActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId){
        // todo get the user from the user that is logged in
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: received request , userId {}",methodName,userId);
        DeviceListResponseDto deviceListResponseDto = new DeviceListResponseDto();
        ResponseEntity<DeviceListResponseDto> responseEntity;

        DeviceDto deviceDto = new DeviceDto();
        BaseResponseDetailDto responseDtoDetail = new BaseResponseDetailDto();
        ArrayList<DeviceDto> deviceDtos = new ArrayList<>();
        try {
            ArrayList<Device> devices = deviceService.getDeviceToActivate(userId);
            deviceDtos = deviceMapper.toDtos(devices);
            responseDtoDetail.setResultStatus(ResultStatusEnum.SUCCESS);
            deviceListResponseDto.setResponseDetail(responseDtoDetail);
            responseEntity = new ResponseEntity<>(deviceListResponseDto,HttpStatus.OK);
        } catch (MissingFieldException e) {
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(e.getMessage());
            responseEntity = new ResponseEntity<>(deviceListResponseDto,HttpStatus.BAD_REQUEST);
            logger.error("{}: userId {} error {}",methodName,userId,e.getMessage());
            e.printStackTrace();
        } catch (UserNotExistException e) {
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(e.getMessage());
            responseEntity = new ResponseEntity<>(deviceListResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: userId {} error {}",methodName,userId,e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(MessageConst.GENERIC_ERROR);
            responseEntity = new ResponseEntity<>(deviceListResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: userId {} error {}",methodName,userId,e.getMessage());
            e.printStackTrace();
        }
        logger.info("{}: userId {} result : {} ",methodName,userId,deviceListResponseDto);
        return responseEntity;
    }
    @Override
    public ResponseEntity<DeviceDto> deviceActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @PathVariable String deviceId){
        // todo get the user from the user that is logged in
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: received request to activate device with id {}, userId {}",methodName,deviceId,userId);
        ResponseEntity<DeviceDto> responseEntity;
        BaseResponseDetailDto responseDtoDetail = new BaseResponseDetailDto();
        DeviceDto deviceDto = new DeviceDto();
        try {
            Device device = deviceService.activate(userId, deviceId);
            // convert to dto
            deviceDto = deviceMapper.toDto(device);
            responseEntity = new ResponseEntity<>(deviceDto,HttpStatus.OK);
        } catch (MissingFieldException e) {
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(e.getMessage());
            deviceDto.setResponseDetail(responseDtoDetail);
            responseEntity = new ResponseEntity<>(deviceDto,HttpStatus.BAD_REQUEST);
            logger.error("{}: activate device with id  {} error {}",methodName,deviceId,e.getMessage());
            e.printStackTrace();
        } catch (UserNotExistException e) {
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(e.getMessage());
            deviceDto.setResponseDetail(responseDtoDetail);
            responseEntity = new ResponseEntity<>(deviceDto,HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: activate device with id  {} error {}",methodName,deviceId,e.getMessage());
            e.printStackTrace();
        }catch (DeviceAlreadyActivated e){
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(MessageConst.DEVICE_ALREADY_ACTIVATED);
            deviceDto.setResponseDetail(responseDtoDetail);
            responseEntity = new ResponseEntity<>(deviceDto,HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: activate device with id  {} error {}",methodName,deviceId,e.getMessage());
            e.printStackTrace();
        }catch (DeviceWrongStatusException e) {
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(MessageConst.DEVICE_NOT_IN_TO_ACTIVATED_STATUS);
            deviceDto.setResponseDetail(responseDtoDetail);
            responseEntity = new ResponseEntity<>(deviceDto, HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: activate device with id  {} error {}", methodName, deviceId, e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            responseDtoDetail.setResultStatus(ResultStatusEnum.FAILED);
            responseDtoDetail.setMessage(MessageConst.GENERIC_ERROR);
            deviceDto.setResponseDetail(responseDtoDetail);
            responseEntity = new ResponseEntity<>(deviceDto,HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: activate device with id  {} error {}",methodName,deviceId,e.getMessage());
            e.printStackTrace();
        }
        logger.info("{}:request to activate device with id {}, userId {} done, deviceDtoResponseEntity {}",methodName,deviceId,userId,responseEntity);
        return responseEntity;

    }
}
