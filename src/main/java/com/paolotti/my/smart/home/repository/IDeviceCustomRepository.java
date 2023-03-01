package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.DeviceEntity;

import java.util.ArrayList;

public interface IDeviceCustomRepository {
    DeviceEntity save(DeviceEntity deviceEntity);

    ArrayList<DeviceEntity> findAllByMacAddressAndNotDeactivated(String macAddress);

    ArrayList<DeviceEntity>  findAllByUserAndToActivate(String userId);

    DeviceEntity findActiveById(String deviceId);
}
