package com.paolotti.my.smart.home.factory.impl;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.factory.IBeanFactoryService;
import com.paolotti.my.smart.home.repository.DeviceRepository;
import com.paolotti.my.smart.home.service.IDeviceService;
import com.paolotti.my.smart.home.service.impl.DeviceNoBrandServiceImpl;
import com.paolotti.my.smart.home.service.impl.DevicePaolottiServiceImpl;
import com.paolotti.my.smart.home.service.impl.DeviceYeelightServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeanFactoryServiceImpl implements IBeanFactoryService {
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    DeviceRepository deviceRepository;
    private static final Logger logger = LoggerFactory.getLogger(BeanFactoryServiceImpl.class);
    private static final String LOG_CASE_BEAN_RETRIEVED ="case {} bean retrieved {}";
    @Override
    public IDeviceService getDeviceServiceById(String deviceId) throws GenericException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        IDeviceService deviceService;
        if(deviceId==null){
            throw new ValidationException("deviceId cannot be null");
        }
        Optional<DeviceBrandEnum> deviceBrandEnumOpt = deviceRepository.findBrandByDeviceId(deviceId);
        if(deviceBrandEnumOpt.isPresent()){
            DeviceBrandEnum deviceBrandEnum = deviceBrandEnumOpt.get();
            deviceService = getDeviceByBrand(deviceBrandEnumOpt.get());
        }
        else {
            throw new GenericException("no brand defined for deviceId " + deviceId);
        }
        logger.debug("{} :the device service impl for device {} was correctly retrieved",methodName,deviceId);
        return deviceService;
    }

    @Override
    public IDeviceService getDeviceByBrand(DeviceBrandEnum deviceBrandEnum) throws GenericException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        IDeviceService deviceService;
        switch (deviceBrandEnum){
            case PAOLOTTI:
                deviceService = appContext.getBean(DevicePaolottiServiceImpl.class);
                logger.debug(LOG_CASE_BEAN_RETRIEVED,deviceBrandEnum,deviceService.getClass().getName());
                break;
            case YEELIGHT:
                deviceService = appContext.getBean(DeviceYeelightServiceImpl.class);
                logger.debug(LOG_CASE_BEAN_RETRIEVED,deviceBrandEnum,deviceService.getClass().getName());
                break;
            case NO_BRAND:
                deviceService = appContext.getBean(DeviceNoBrandServiceImpl.class);
                logger.debug(LOG_CASE_BEAN_RETRIEVED,deviceBrandEnum,deviceService.getClass().getName());
                break;
            default:
                throw new BrandNotSupportedException(deviceBrandEnum);
        }
        logger.debug("{} : getting the device service impl for brand {}",methodName,deviceBrandEnum);
        return deviceService;
    }
}
