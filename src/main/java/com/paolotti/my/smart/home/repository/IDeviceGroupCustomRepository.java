package com.paolotti.my.smart.home.repository;


import com.paolotti.my.smart.home.repository.entity.GroupDeviceEntity;

public interface IDeviceGroupCustomRepository {
    GroupDeviceEntity save(GroupDeviceEntity groupDeviceEntity);
    GroupDeviceEntity findById(String groupId);
}
