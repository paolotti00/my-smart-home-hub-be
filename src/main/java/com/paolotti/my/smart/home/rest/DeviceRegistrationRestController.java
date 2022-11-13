package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.constant.MessageConst;
import com.paolotti.my.smart.home.constant.RestConst;
import com.paolotti.my.smart.home.exception.DeviceAlreadyRegisteredException;
import com.paolotti.my.smart.home.exception.DeviceCreationException;
import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.exception.UserNotExistException;
import com.paolotti.my.smart.home.rest.dto.*;
import com.paolotti.my.smart.home.service.IDeviceRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("device/registration")
public class DeviceRegistrationRestController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceRegistrationRestController.class);
    @Autowired
    IDeviceRegistrationService deviceService;

    @PostMapping("")
    ResponseEntity<DeviceRegistrationResponseDto> handleDeviceRegistrationRequest(@RequestHeader(RestConst.HEADER_USER_ID) String userId,@RequestBody  DeviceRegistrationRequestDto registrationRequestDto) {
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
    @GetMapping("toactivate")
    ResponseEntity<GetDevicesToActivateResponseDto> getDevicesToActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId){
        // todo get the user from the user that is logged in
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ResponseEntity<GetDevicesToActivateResponseDto> getDevicesToActivateResponseDtoResponseEntity;
        GetDevicesToActivateResponseDto getDevicesToActivateResponseDto = new GetDevicesToActivateResponseDto();
        ArrayList<DeviceDto> deviceDtos;
        logger.info("{}: received request , userId {}",methodName,userId);
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
}
