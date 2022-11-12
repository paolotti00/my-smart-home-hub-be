package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.enums.DeviceCreationResultStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.exception.DeviceAlreadyRegisteredException;
import com.paolotti.my.smart.home.exception.DeviceCreationException;
import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.mapper.DeviceMapper;
import com.paolotti.my.smart.home.mapper.IDeviceRegistrationMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceRegistrationRequest;
import com.paolotti.my.smart.home.model.DeviceRegistrationResponse;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationRequestDto;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationResponseDto;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.paolotti.my.smart.home.constant.AttributeNameConst.*;
import static com.paolotti.my.smart.home.constant.MessageConst.DEVICE_ALREADY_REGISTERED;

@Service
public class DeviceServiceImpl implements IDeviceService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    IDeviceRegistrationMapper deviceRegistrationMapper;

    @Override
    public DeviceRegistrationResponseDto deviceSelfRegisteringHandling(DeviceRegistrationRequestDto deviceRegistrationRequestDto){
        // handling a device self registration request
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device auto register flow started, deviceRegistrationRequestDto {}",methodName,deviceRegistrationRequestDto);
        // convert dto to entity
        DeviceRegistrationRequest deviceRegistrationRequest = deviceRegistrationMapper.toDeviceRegistrationRequest(deviceRegistrationRequestDto);
        // request validation
        // checking if the device already exist
        Device retrievedDevice = getDeviceByMacAddress(deviceRegistrationRequest.getDeviceMacAddress());
        logger.info("validation of request start");
        if(retrievedDevice!=null){
            // this device is already registered
            logger.error("the device already exist");
            throw new DeviceAlreadyRegisteredException(DEVICE_ALREADY_REGISTERED);
        }
        if(deviceRegistrationRequest.getUserId()==null){
            logger.error("the field {} is missing", DEVICE_REG_REQ_USER_ID_ATTRIBUTE_NAME);
            throw new MissingFieldException(DEVICE_REG_REQ_USER_ID_ATTRIBUTE_NAME);
        }
        if(deviceRegistrationRequest.getDeviceType()==null){
            logger.error("the field {} is missing", DEVICE_REG_REQ_TYPE_ATTRIBUTE_NAME);
            throw new MissingFieldException(DEVICE_REG_REQ_TYPE_ATTRIBUTE_NAME);
        }
        if(deviceRegistrationRequest.getDeviceMacAddress()==null){
            logger.error("the field {} is missing", DEVICE_REG_REQ_MAC_ADDRESS_ATTRIBUTE_NAME);
            throw new MissingFieldException(DEVICE_REG_REQ_MAC_ADDRESS_ATTRIBUTE_NAME);
        }
        logger.info("validation of request done");
        // create and registering the device
        DeviceRegistrationResponse deviceRegistrationResponse = new DeviceRegistrationResponse();
        try {
            Device deviceToCreate = deviceMapper.toDevice(deviceRegistrationRequest);
            deviceToCreate.setCreationDate(LocalDateTime.now());
            deviceRegistrationResponse.setCreatedDevice(createDeviceInToActivateStatus(deviceToCreate));
            deviceRegistrationResponse.setCreationResult(DeviceCreationResultStatusEnum.CREATED);
        } catch (DeviceCreationException e) {
            logger.error("something went wrong during the device creation. message : {}",e.getMessage());
            deviceRegistrationResponse.setCreationResult(DeviceCreationResultStatusEnum.FAILED);
            deviceRegistrationResponse.setCreationErrorMsg(e.getMessage());
        }
        DeviceRegistrationResponseDto deviceRegistrationResponseDto = deviceRegistrationMapper.toDeviceRegistrationResponseDto(deviceRegistrationResponse);
        logger.info("{}: device auto register flow finished, device {}",methodName,deviceRegistrationResponse);
        return  deviceRegistrationResponseDto;


    }
    private  <T extends Device> T  getDeviceByMacAddress (String macAddress){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        T device = null;
        logger.info("{}: getting device with macAddress {}",methodName,macAddress);
        // todo pt
        logger.info("{}: getting device with macAddress {} found",methodName,device);
        return device;
    };
    private Device createDeviceInToActivateStatus(Device device) throws DeviceCreationException {
        // this method care to create new device that was discovered
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device creation started, device to create in to activate status {}",methodName,device);
        device.setInstallationStatus(DeviceInstallationStatusEnum.TO_ACTIVATE);
        Device createdDevice = new Device(); // TODO pt
        logger.info("{}: device creation finished, created device in to activate status  {}",methodName,createdDevice);
        return createdDevice;
    }
}
