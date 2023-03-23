package com.paolotti.my.smart.home.factory;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.service.deprecated.IDeviceByBrandService;

public interface IBeanFactoryService {
    IDeviceByBrandService getDeviceLightByBrandServiceImpl(DeviceBrandEnum deviceBrandEnum) throws BrandNotSupportedException;
}
