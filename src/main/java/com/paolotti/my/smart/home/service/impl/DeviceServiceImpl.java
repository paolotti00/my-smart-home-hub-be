package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.exception.DeviceAlreadyRegisteredException;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.paolotti.my.smart.home.constant.MessageConst.DEVICE_ALREADY_REGISTERED;

@Service
public class DeviceServiceImpl implements IDeviceService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Override
    public <T extends Device> T deviceSelfRegistering(T device){
        // handling a device self registration request
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: started device auto register flow, device {}",methodName,device);
        // checking if device already exist
        Device retrievedDevise = getDeviceByMacAddress(device.getMacAddress());
        if(retrievedDevise!=null){
            throw new DeviceAlreadyRegisteredException(DEVICE_ALREADY_REGISTERED);
        }

        logger.info("{}: device auto register flow finished, device {}",methodName,device);
        return  device;


    }
    private  <T extends Device> T  getDeviceByMacAddress (String macAddress){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        T device = null;
        logger.info("{}: getting device with macAddress {}",methodName,macAddress);
        // todo pt
        logger.info("{}: getting device with macAddress {} found",methodName,device);
        return device;
    };
}
