package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.GroupNotExistsException;

public interface IGroupService {
    // light
    void switchAllLightsByGroup(String userId, String groupId, OnOffStatusEnum desiredStatus) throws GroupNotExistsException;
}
