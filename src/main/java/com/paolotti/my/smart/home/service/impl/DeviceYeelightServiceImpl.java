package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.model.AckCommand;
import com.paolotti.my.smart.home.model.ExtraActionCommandData;
import com.paolotti.my.smart.home.model.PingDeviceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceYeelightServiceImpl extends DeviceAbstractServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(DeviceYeelightServiceImpl.class);


    @Override
    public void switchAllLights(String userId, String deviceId, String roomId, OnOffStatusEnum desiredStatus, CommandDestinationTypeEnum commandDestinationTypeEnum) throws GenericException {
        throw new BrandNotSupportedException(String.format("%s is not yet supported by brand %s", Thread.currentThread().getStackTrace()[1].getMethodName(), DeviceBrandEnum.YEELIGHT));
    }


    @Override
    public List<ExtraActionCommandData> getSupportedExtraActions(String deviceId) throws BrandNotSupportedException {
        throw new BrandNotSupportedException(String.format("%s is not yet supported by brand %s", Thread.currentThread().getStackTrace()[1].getMethodName(), DeviceBrandEnum.YEELIGHT));
    }

    @Override
    public void setLightColor(String userId, String deviceId,String roomId, List<Integer> colorRgbAndIntensity, CommandDestinationTypeEnum toDevice) throws GenericException {
        throw new BrandNotSupportedException(String.format("%s is not yet supported by brand %s", Thread.currentThread().getStackTrace()[1].getMethodName(), DeviceBrandEnum.YEELIGHT));
    }

    @Override
    public void doExtraAction(String userId, String deviceId, String roomId, ExtraActionCommandData action, CommandDestinationTypeEnum commandDestinationTypeEnum) throws GenericException,BrandNotSupportedException {
        throw new BrandNotSupportedException(String.format("%s is not yet supported by brand %s", Thread.currentThread().getStackTrace()[1].getMethodName(), DeviceBrandEnum.YEELIGHT));
    }

    @Override
    public void updateDeviceStatusFromAckReceived(AckCommand ackCommand) throws ValidationException, DeviceNotExistsException, BrandNotSupportedException {
        throw new BrandNotSupportedException(String.format("%s is not yet supported by brand %s", Thread.currentThread().getStackTrace()[1].getMethodName(), DeviceBrandEnum.YEELIGHT));
    }

    @Override
    public void handleDeviceStatusFromPingReceived(PingDeviceStatus pingDeviceStatus) throws ValidationException, DeviceNotExistsException, BrandNotSupportedException {
        throw new BrandNotSupportedException(String.format("%s is not yet supported by brand %s", Thread.currentThread().getStackTrace()[1].getMethodName(), DeviceBrandEnum.YEELIGHT));
    }
}