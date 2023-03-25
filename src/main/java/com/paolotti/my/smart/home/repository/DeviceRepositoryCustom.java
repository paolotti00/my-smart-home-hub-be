package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface DeviceRepositoryCustom {
    ArrayList<DeviceEntity> findAllByMacAddressAndNotDeactivated(String macAddress);

    ArrayList<DeviceEntity>  findAllByUserAndToActivate(String userId);

    DeviceEntity findActiveById(String deviceId);

    Optional<DeviceBrandEnum> findBrandByDeviceId(String deviceId) throws DeviceNotExistsException;
}
