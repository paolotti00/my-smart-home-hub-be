package com.paolotti.my.smart.home.repository;

import com.paolotti.my.smart.home.repository.entity.DeviceEntity;

import java.util.ArrayList;

public interface IDeviceCustomRepository {
    <T extends DeviceEntity> T save(T deviceEntity);


    <T extends DeviceEntity> ArrayList<T> findAllByMacAddressAndNotDeactivated(String macAddress, Class<T> clazz);

    <T extends DeviceEntity> ArrayList<T> findAllByUserAndToActivate(String userId);

    <T extends DeviceEntity> T findById(String deviceId);
}
