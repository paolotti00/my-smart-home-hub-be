package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.DeviceEntity;

import java.util.ArrayList;

public interface DeviceRepositoryCustom {
    ArrayList<DeviceEntity> findAllByMacAddressAndNotDeactivated(String macAddress);

    ArrayList<DeviceEntity>  findAllByUserAndToActivate(String userId);

    DeviceEntity findActiveById(String deviceId);
}
