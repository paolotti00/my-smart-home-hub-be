package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.constant.MessageConst;
import com.paolotti.my.smart.home.constant.RestConst;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.rest.IDeviceRegistrationRestController;
import com.paolotti.my.smart.home.rest.dto.*;
import com.paolotti.my.smart.home.rest.dto.reqres.*;
import com.paolotti.my.smart.home.service.IDeviceRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
public class DeviceRegistrationRestController implements IDeviceRegistrationRestController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceRegistrationRestController.class);
    @Autowired
    IDeviceRegistrationService deviceService;

    @Override
    public ResponseEntity<DeviceRegistrationResponseDto> handleDeviceRegistrationRequest(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @RequestBody DeviceRegistrationRequestDto registrationRequestDto) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device auto register flow received request dto, deviceRegistrationRequestDto {}",methodName,registrationRequestDto);
        ResponseEntity<DeviceRegistrationResponseDto> registrationResponseDtoResponseEntity;
        DeviceRegistrationResponseDto deviceRegistrationResponseDto = new DeviceRegistrationResponseDto();
        try {
            deviceRegistrationResponseDto = deviceService.deviceSelfRegisteringHandling(userId,registrationRequestDto);
            deviceRegistrationResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.SUCCESS);
            registrationResponseDtoResponseEntity = new ResponseEntity<>(deviceRegistrationResponseDto, HttpStatus.OK);
        } catch (MissingFieldException e) {
            deviceRegistrationResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            deviceRegistrationResponseDto.setMessage(e.getMessage());
            registrationResponseDtoResponseEntity = new ResponseEntity<>(deviceRegistrationResponseDto, HttpStatus.BAD_REQUEST);
            logger.info("{}: device auto register flow error: {}",methodName,e.getMessage());
            e.printStackTrace();
        } catch (DeviceAlreadyRegisteredException | DeviceCreationException e) {
            deviceRegistrationResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            deviceRegistrationResponseDto.setMessage(e.getMessage());
            registrationResponseDtoResponseEntity = new ResponseEntity<>(deviceRegistrationResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
            logger.info("{}: device auto register flow error: {}",methodName,e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            deviceRegistrationResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            deviceRegistrationResponseDto.setMessage(MessageConst.GENERIC_ERROR);
            registrationResponseDtoResponseEntity = new ResponseEntity<>(deviceRegistrationResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
            logger.info("{}: device auto register flow error: {}",methodName,e.getMessage());
            e.printStackTrace();
        }
        logger.info("{}: device auto register flow finished  deviceRegistrationResponseDto {}",methodName,deviceRegistrationResponseDto);
        return registrationResponseDtoResponseEntity;
    };
    @Override
    public ResponseEntity<GetDevicesToActivateResponseDto> getDevicesToActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId){
        // todo get the user from the user that is logged in
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: received request , userId {}",methodName,userId);
        ResponseEntity<GetDevicesToActivateResponseDto> getDevicesToActivateResponseDtoResponseEntity;
        GetDevicesToActivateResponseDto getDevicesToActivateResponseDto = new GetDevicesToActivateResponseDto();
        ArrayList<DeviceDto> deviceDtos;
        try {
            deviceDtos = deviceService.getDeviceToActivate(userId);
            getDevicesToActivateResponseDto.setDevicesList(deviceDtos);
            getDevicesToActivateResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.SUCCESS);
            getDevicesToActivateResponseDtoResponseEntity = new ResponseEntity<>(getDevicesToActivateResponseDto,HttpStatus.OK);
        } catch (MissingFieldException e) {
            getDevicesToActivateResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            getDevicesToActivateResponseDto.setMessage(e.getMessage());
            getDevicesToActivateResponseDtoResponseEntity = new ResponseEntity<>(getDevicesToActivateResponseDto,HttpStatus.BAD_REQUEST);
            logger.error("{}: userId {} error {}",methodName,userId,e.getMessage());
            e.printStackTrace();
        } catch (UserNotExistException e) {
            getDevicesToActivateResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            getDevicesToActivateResponseDto.setMessage(e.getMessage());
            getDevicesToActivateResponseDtoResponseEntity = new ResponseEntity<>(getDevicesToActivateResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: userId {} error {}",methodName,userId,e.getMessage());
            e.printStackTrace();
        } catch (Exception e){
            getDevicesToActivateResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            getDevicesToActivateResponseDto.setMessage(MessageConst.GENERIC_ERROR);
            getDevicesToActivateResponseDtoResponseEntity = new ResponseEntity<>(getDevicesToActivateResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: userId {} error {}",methodName,userId,e.getMessage());
            e.printStackTrace();
        }
        logger.info("{}: userId {} result : {} ",methodName,userId,getDevicesToActivateResponseDto);
        return getDevicesToActivateResponseDtoResponseEntity;
    }
    @Override
    public ResponseEntity<ActivateDeviceResponseDto> deviceActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @PathVariable String deviceId){
        // todo get the user from the user that is logged in
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: received request to activate device with id {}, userId {}",methodName,deviceId,userId);
        ResponseEntity<ActivateDeviceResponseDto> deviceDtoResponseEntity;
        ActivateDeviceResponseDto activateDeviceResponseDto = new ActivateDeviceResponseDto();
        DeviceDto deviceDto = new DeviceDto();
        try {
            deviceDto = deviceService.activate(userId,deviceId);
            activateDeviceResponseDto.setDeviceDto(deviceDto);
            deviceDtoResponseEntity = new ResponseEntity<>(activateDeviceResponseDto,HttpStatus.OK);
        } catch (MissingFieldException e) {
            activateDeviceResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            activateDeviceResponseDto.setMessage(e.getMessage());
            deviceDtoResponseEntity = new ResponseEntity<>(activateDeviceResponseDto,HttpStatus.BAD_REQUEST);
            logger.error("{}: activate device with id  {} error {}",methodName,deviceId,e.getMessage());
            e.printStackTrace();
        } catch (UserNotExistException e) {
            activateDeviceResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            activateDeviceResponseDto.setMessage(e.getMessage());
            deviceDtoResponseEntity = new ResponseEntity<>(activateDeviceResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: activate device with id  {} error {}",methodName,deviceId,e.getMessage());
            e.printStackTrace();
        }catch (DeviceAlreadyActivated e){
            activateDeviceResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            activateDeviceResponseDto.setMessage(MessageConst.DEVICE_ALREADY_ACTIVATED);
            deviceDtoResponseEntity = new ResponseEntity<>(activateDeviceResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: activate device with id  {} error {}",methodName,deviceId,e.getMessage());
            e.printStackTrace();
        }catch (DeviceWrongStatusException e) {
            activateDeviceResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            activateDeviceResponseDto.setMessage(MessageConst.DEVICE_NOT_IN_TO_ACTIVATED_STATUS);
            deviceDtoResponseEntity = new ResponseEntity<>(activateDeviceResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: activate device with id  {} error {}", methodName, deviceId, e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            activateDeviceResponseDto.setResultStatus(BaseResponseDto.ResultStatusEnum.FAILED);
            activateDeviceResponseDto.setMessage(MessageConst.GENERIC_ERROR);
            deviceDtoResponseEntity = new ResponseEntity<>(activateDeviceResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("{}: activate device with id  {} error {}",methodName,deviceId,e.getMessage());
            e.printStackTrace();
        }
        logger.info("{}:request to activate device with id {}, userId {} done, deviceDtoResponseEntity {}",methodName,deviceId,userId,deviceDtoResponseEntity);
        return deviceDtoResponseEntity;

    }
}
