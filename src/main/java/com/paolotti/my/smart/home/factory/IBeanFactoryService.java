package com.paolotti.my.smart.home.factory;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.service.IDeviceLightByBrandService;

public interface IBeanFactoryService {
    IDeviceLightByBrandService getDeviceLightByBrandServiceImpl(DeviceBrandEnum deviceBrandEnum) throws BrandNotSupportedException;
}
