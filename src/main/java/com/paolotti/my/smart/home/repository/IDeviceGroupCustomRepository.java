package com.paolotti.my.smart.home.repository;


import com.paolotti.my.smart.home.repository.entity.DeviceGroupEntity;

public interface IDeviceGroupCustomRepository {
    DeviceGroupEntity save(DeviceGroupEntity deviceGroupEntity);
    DeviceGroupEntity findById(String groupId);
}
