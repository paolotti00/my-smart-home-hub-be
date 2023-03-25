package com.paolotti.my.smart.home.factory;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.service.IDeviceService;

public interface IBeanFactoryService {
    IDeviceService getDeviceServiceById(String deviceId) throws GenericException;
    IDeviceService getDeviceByBrand(DeviceBrandEnum deviceBrandEnum) throws GenericException;
}
