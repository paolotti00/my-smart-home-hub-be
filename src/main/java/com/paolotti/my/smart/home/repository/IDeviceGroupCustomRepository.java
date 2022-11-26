package com.paolotti.my.smart.home.repository;


import com.paolotti.my.smart.home.repository.entity.GroupDeviceEntity;

public interface IDeviceGroupCustomRepository {
    GroupDeviceEntity findById(String groupId);
}
