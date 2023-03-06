package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.constant.MessageConst;
import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.factory.IBeanFactoryService;
import com.paolotti.my.smart.home.mapper.deprecated.IDeviceMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.User;
import com.paolotti.my.smart.home.repository.DeviceRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.service.IRegistrationDeviceService;
import com.paolotti.my.smart.home.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.paolotti.my.smart.home.constant.AttributeNameConst.*;
import static com.paolotti.my.smart.home.constant.MessageConst.DEVICE_ALREADY_REGISTERED;

@Service
public class RegistrationDeviceServiceImpl implements IRegistrationDeviceService {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationDeviceServiceImpl.class);
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    IUserService userService;
    @Autowired
    IBeanFactoryService beanFactoryService;
    @Autowired
    IDeviceMapper deviceMapper;

    @Override
    public Device deviceSelfRegisteringHandling(String userId, Device device) throws DeviceAlreadyRegisteredException, MissingFieldException, DeviceCreationException, UserNotExistException {
        // handling a device self registration request
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: device auto register flow started, device to register {}",methodName,device);
        // request validation
        // checking if the device already exist
        List<Device> retrievedDevices = getNotDeactivateDeviceByMacAddress(device.getNetworkData().getMacAddress());
        logger.info("validation of request start");
        if(retrievedDevices!=null && !retrievedDevices.isEmpty()){
            // this device is already registered
            logger.error("the device already exist, devices found : {}",retrievedDevices);
            throw new DeviceAlreadyRegisteredException(DEVICE_ALREADY_REGISTERED);
        }
        // user
        User user = null;
        // enough one between user id or email is mandatory on this flow
        // if(userId==null && (device.getUser()==null || device.getUser().getEmail()==null) ){
        if(userId==null && (device.getUsersOwnersIds()==null) ){
            logger.error("the fields {} and {} are missing. enough one must be present", USER_ID_ATTRIBUTE_NAME, USER_ID_ATTRIBUTE_USER_EMAIL);
            throw new MissingFieldException(USER_ID_ATTRIBUTE_NAME + "and" +"USER_ID_ATTRIBUTE_USER_EMAIL");
        } else {
            // checking if user exist and if yes getting it
            user= userService.checkIfUserExistsByIdOrEmailAndRetrieve(userId, null);
        }
        if(device.getNetworkData() == null || device.getNetworkData().getMacAddress()==null){
            logger.error("the field {} is missing", DEVICE_REG_REQ_MAC_ADDRESS_ATTRIBUTE_NAME);
            throw new MissingFieldException(DEVICE_REG_REQ_MAC_ADDRESS_ATTRIBUTE_NAME);
        }
        logger.info("validation of request done");
        // create and registering the device
        try {
            //device.setUser(user);
            device.setCreationDate(LocalDateTime.now());
            device = createDeviceInToActivateStatus(device);
        } catch (DeviceCreationException e) {
            logger.error("something went wrong during the device creation. message : {}",e.getMessage());
            throw e;
        }
        logger.info("{}: device auto register flow finished, device {}",methodName,device);
        return  device;


    }
    @Override
    public ArrayList<Device> getDeviceToActivate(String userId) throws MissingFieldException, UserNotExistException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: getting device to activate for the user {}",methodName,userId);
        // validation
        logger.info("validation of request start");
        if(userId==null){
            logger.error("the field {} is missing", USER_ID_ATTRIBUTE_NAME);
            throw new MissingFieldException(USER_ID_ATTRIBUTE_NAME);
        }
        // check if the user exist
        userService.checkIfUserExistsAndRetrieve(userId);
        logger.info("validation of request done");
        List<DeviceEntity> devicesEntity = deviceRepository.findAllByUserAndToActivate(userId);
        List<Device> devices = deviceMapper.toModelList(devicesEntity);
        logger.info("{}: found {} devices to activate for the user {}, devices ",methodName,userId,devices);
        return null;
    }
    @Override
    public Device activate (String userId,String deviceId) throws MissingFieldException, UserNotExistException, DeviceNotExistsException, DeviceAlreadyActivated, DeviceWrongStatusException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: activation device id {} with userId {} start",methodName,deviceId,userId);
        // validate request
        logger.info("validation of request start");
        if(deviceId==null){
            logger.error("the field {} is missing", DEVICE_ID_ATTRIBUTE_NAME);
            throw new MissingFieldException(DEVICE_ID_ATTRIBUTE_NAME);
        }
        if(userId==null){
            logger.error("the field {} is missing", USER_ID_ATTRIBUTE_NAME);
            throw new MissingFieldException(USER_ID_ATTRIBUTE_NAME);
        }
        // check if the user exist
        userService.checkIfUserExistsAndRetrieve(userId);
        logger.info("validation of request done");
        // check if the device exist and retrieving it
        // retrieving the device
        Device device = checkIfDeviceExistsAndRetrieve(deviceId);
        // check if is not already activated
        if(device.getInstallationStatus()== DeviceInstallationStatusEnum.ACTIVE){
            logger.error(MessageConst.DEVICE_ALREADY_ACTIVATED);
            throw new DeviceAlreadyActivated(deviceId);
        }
        // check if is in to_activate status
        if(device.getInstallationStatus() != DeviceInstallationStatusEnum.TO_ACTIVATE){
            logger.error(MessageConst.DEVICE_NOT_IN_TO_ACTIVATED_STATUS);
            throw new DeviceWrongStatusException(deviceId,DeviceInstallationStatusEnum.TO_ACTIVATE);
        }
        device.setInstallationStatus(DeviceInstallationStatusEnum.ACTIVE);
        device.setActivationDate(LocalDateTime.now());
        DeviceEntity deviceEntity = deviceMapper.toEntity(device);
        deviceEntity = deviceRepository.save(deviceEntity);
        device = deviceMapper.toModel(deviceEntity);
        logger.info("{}: the device with id {} and userId {} was activated - device {}",methodName,userId,deviceId,device);
        return device;

    }
    @Override
    public Device getDeviceById(String deviceId) throws DeviceNotExistsException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: getting deviceId {}",methodName,deviceId);
        DeviceEntity deviceEntity = deviceRepository.findActiveById(deviceId);
        if(deviceEntity==null){
            logger.error("user userId {} not exist",deviceId);
            throw new DeviceNotExistsException(deviceId);
        }
        Device device = deviceMapper.toModel(deviceEntity);
        logger.info("{}: userId {} found, user ",methodName,device);
        return device;
    }

    private List<Device> getNotDeactivateDeviceByMacAddress(String macAddress){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: getting device with macAddress {}",methodName,macAddress);
        ArrayList<DeviceEntity> deviceEntities = deviceRepository.findAllByMacAddressAndNotDeactivated(macAddress);
        List<Device> foundDevices =  deviceMapper.toModelList(deviceEntities);
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
        DeviceEntity deviceEntityResult = deviceRepository.save(deviceEntity);
        // to model
        Device createdDevice = deviceMapper.toModel(deviceEntityResult);
        logger.info("{}: device creation finished, created device in to activate status  {}",methodName,createdDevice);
        return createdDevice;
    }
    @Override
    public Device checkIfDeviceExistsAndRetrieve(String deviceId) throws DeviceNotExistsException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: checking if the device with deviceId {} exist",methodName,deviceId);
        Device device = getDeviceById(deviceId);
        if(device==null){
            throw new DeviceNotExistsException(deviceId);
        }
        logger.info("{}: the device with deviceId {} exist",methodName,deviceId);
        return device;
    }
}
