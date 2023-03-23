package com.paolotti.my.smart.home.factory.impl;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.factory.IBeanFactoryService;
import com.paolotti.my.smart.home.service.deprecated.IDeviceByBrandService;
import com.paolotti.my.smart.home.service.deprecated.impl.DeviceByBrandPaolottiServiceImpl;
import com.paolotti.my.smart.home.service.deprecated.impl.DeviceByBrandYeelightServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BeanFactoryServiceImpl implements IBeanFactoryService {
    @Autowired
    private ApplicationContext appContext;
    private static final Logger logger = LoggerFactory.getLogger(BeanFactoryServiceImpl.class);
    private static final String LOG_CASE_BEAN_RETRIEVED ="case {} bean retrieved {}";
    @Override
    public IDeviceByBrandService getDeviceLightByBrandServiceImpl(DeviceBrandEnum deviceBrandEnum) throws BrandNotSupportedException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.debug("{} : getting the device light service impl for brand {}",methodName,deviceBrandEnum);
        IDeviceByBrandService deviceLightService;
        switch (deviceBrandEnum){
            case PAOLOTTI:
                deviceLightService = appContext.getBean(DeviceByBrandPaolottiServiceImpl.class);
                logger.debug(LOG_CASE_BEAN_RETRIEVED,deviceBrandEnum,deviceLightService.getClass().getName());
                break;
            case YEELIGHT:
                deviceLightService = appContext.getBean(DeviceByBrandYeelightServiceImpl.class);
                logger.debug(LOG_CASE_BEAN_RETRIEVED,deviceBrandEnum,deviceLightService.getClass().getName());
                break;
            default:
                throw new BrandNotSupportedException(deviceBrandEnum);
        }
        logger.debug("{} :the device light service impl for brand {} was correctly retrieved",methodName,deviceBrandEnum);
        return deviceLightService;
    }
}
