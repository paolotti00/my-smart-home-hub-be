package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.exception.DeviceAlreadyRegisteredException;
import com.paolotti.my.smart.home.exception.DeviceCreationException;
import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.exception.UserNotExistException;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.mapper.IDeviceRegistrationMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceRegistrationRequest;
import com.paolotti.my.smart.home.model.DeviceRegistrationResponse;
import com.paolotti.my.smart.home.model.User;
import com.paolotti.my.smart.home.repository.IDeviceCustomRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationRequestDto;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationResponseDto;
import com.paolotti.my.smart.home.service.IDeviceRegistrationService;
import com.paolotti.my.smart.home.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.paolotti.my.smart.home.constant.AttributeNameConst.*;
import static com.paolotti.my.smart.home.constant.MessageConst.DEVICE_ALREADY_REGISTERED;

@Service
public class DeviceRegistrationServiceImpl implements IDeviceRegistrationService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceRegistrationServiceImpl.class);
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    IDeviceRegistrationMapper deviceRegistrationMapper;
    @Autowired
    IDeviceCustomRepository deviceCustomRepository;
    @Autowired
    IUserService userService;

    @Override
    public DeviceRegistrationResponseDto deviceSelfRegisteringHandling(String userId, DeviceRegistrationRequestDto deviceRegistrationRequestDto) throws DeviceAlreadyRegisteredException, MissingFieldException, DeviceCreationException, UserNotExistException {
        // handling a device self registration request
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device auto register flow started, deviceRegistrationRequestDto {}",methodName,deviceRegistrationRequestDto);
        // convert dto to entity
        DeviceRegistrationRequest deviceRegistrationRequest = deviceRegistrationMapper.toDeviceRegistrationRequest(deviceRegistrationRequestDto);
        // request validation
        // checking if the device already exist
        ArrayList<Device> retrievedDevices = getNotDeactivateDeviceByMacAddress(deviceRegistrationRequest.getNetworkData().getMacAddress());
        logger.info("validation of request start");
        if(retrievedDevices!=null && !retrievedDevices.isEmpty()){
            // this device is already registered
            logger.error("the device already exist, devices found : {}",retrievedDevices);
            throw new DeviceAlreadyRegisteredException(DEVICE_ALREADY_REGISTERED);
        }
        // user
        User user = null;
        if(userId==null){
            logger.error("the field {} is missing", USER_ID_ATTRIBUTE_NAME);
            throw new MissingFieldException(USER_ID_ATTRIBUTE_NAME);
        } else {
            // checking if user exist and if yes getting it
            user= userService.checkIfUserExistsAndRetrieve(userId);
        }
        if(deviceRegistrationRequest.getDeviceType()==null){
            logger.error("the field {} is missing", DEVICE_REG_REQ_TYPE_ATTRIBUTE_NAME);
            throw new MissingFieldException(DEVICE_REG_REQ_TYPE_ATTRIBUTE_NAME);
        }
        if(deviceRegistrationRequest.getNetworkData() == null || deviceRegistrationRequest.getNetworkData().getMacAddress()==null){
            logger.error("the field {} is missing", DEVICE_REG_REQ_MAC_ADDRESS_ATTRIBUTE_NAME);
            throw new MissingFieldException(DEVICE_REG_REQ_MAC_ADDRESS_ATTRIBUTE_NAME);
        }
        logger.info("validation of request done");
        // create and registering the device
        DeviceRegistrationResponse deviceRegistrationResponse = new DeviceRegistrationResponse();
        try {
            Device deviceToCreate = deviceMapper.toDevice(deviceRegistrationRequest);
            deviceToCreate.setUser(user);
            deviceToCreate.setCreationDate(LocalDateTime.now());
            deviceRegistrationResponse.setCreatedDevice(createDeviceInToActivateStatus(deviceToCreate));
        } catch (DeviceCreationException e) {
            logger.error("something went wrong during the device creation. message : {}",e.getMessage());
            throw e;
        }
        DeviceRegistrationResponseDto deviceRegistrationResponseDto = deviceRegistrationMapper.toDeviceRegistrationResponseDto(deviceRegistrationResponse);
        logger.info("{}: device auto register flow finished, device {}",methodName,deviceRegistrationResponse);
        return  deviceRegistrationResponseDto;


    }
    @Override
    public ArrayList<DeviceDto> getDeviceToActivate(String userId) throws MissingFieldException, UserNotExistException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: getting device to activate for the user {}",methodName,userId);
        ArrayList<DeviceDto> devicesDto = new ArrayList<DeviceDto>();
        // validation
        logger.info("validation of request start");
        if(userId==null){
            logger.error("the field {} is missing", USER_ID_ATTRIBUTE_NAME);
            throw new MissingFieldException(USER_ID_ATTRIBUTE_NAME);
        }
        // check if the user exist
        userService.checkIfUserExistsAndRetrieve(userId);
        logger.info("validation of request done");
        ArrayList<DeviceEntity> devicesEntity = deviceCustomRepository.findAllByUserAndToActivate(userId);
        ArrayList<Device> devices = deviceMapper.toModels(devicesEntity);
        ArrayList<DeviceDto> deviceDtos = deviceMapper.toDtos(devices);
        logger.info("{}: found {} devices to activate for the user {}, devices ",methodName,userId,devicesDto);
        return deviceDtos;
    }
    private  ArrayList<Device> getNotDeactivateDeviceByMacAddress(String macAddress){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: getting device with macAddress {}",methodName,macAddress);
        ArrayList<DeviceEntity> deviceEntities = deviceCustomRepository.findAllByMacAddressAndNotDeactivated(macAddress);
        ArrayList<Device> foundDevices = (ArrayList<Device>) deviceMapper.toModels(deviceEntities);
        logger.info("{}: getting device with macAddress {} found",methodName,foundDevices);
        return foundDevices;
    };
    private Device createDeviceInToActivateStatus(Device device) throws DeviceCreationException {
        // this method care to create new device that was discovered
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device creation started, device to create in to activate status {}",methodName,device);
        device.setInstallationStatus(DeviceInstallationStatusEnum.TO_ACTIVATE);
        // to entity
        DeviceEntity deviceEntity = deviceMapper.toEntity(device);
        // save
        DeviceEntity deviceEntityResult = deviceCustomRepository.save(deviceEntity);
        // to model
        Device createdDevice = deviceMapper.toModel(deviceEntityResult);
        logger.info("{}: device creation finished, created device in to activate status  {}",methodName,createdDevice);
        return createdDevice;
    }
}
