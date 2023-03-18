package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.RoomNotExistsException;
import com.paolotti.my.smart.home.exception.ValidationException;

public interface IGroupService {
    // light
    void switchAllLightsByRoom(String userId, String groupId, OnOffStatusEnum desiredStatus) throws RoomNotExistsException, ValidationException;
}
