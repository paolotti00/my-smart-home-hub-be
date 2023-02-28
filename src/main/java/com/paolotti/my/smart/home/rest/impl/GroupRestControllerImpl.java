package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.GroupNotExistsException;
import com.paolotti.my.smart.home.rest.IGroupRestController;
import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.service.IGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GroupRestControllerImpl implements IGroupRestController {
    @Autowired
    IGroupService groupService;
    private static final Logger logger = LoggerFactory.getLogger(GroupRestControllerImpl.class);
    @Override
    public ResponseEntity<BaseResponseDto> switchOnAllLightsByGroup(String groupId) throws GroupNotExistsException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: group id {}", methodName, groupId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto> responseEntity;
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            groupService.switchAllLightsByGroup(userId, groupId, OnOffStatusEnum.ON);
            baseResponseDto.setMessage(String.format("devices of group %s correctly switched %s", groupId, OnOffStatusEnum.ON));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<BaseResponseDto> switchOffAllLightsByGroup(String groupId) throws GroupNotExistsException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: group id {}", methodName, groupId);
        String userId = "ex"; // todo should be retrieved from spring Principal?
        ResponseEntity<BaseResponseDto> responseEntity;
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            groupService.switchAllLightsByGroup(userId, groupId, OnOffStatusEnum.OFF);
            baseResponseDto.setMessage(String.format("devices of group %s correctly switched %s", groupId, OnOffStatusEnum.OFF));
            responseEntity = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("{} error", methodName);
            throw e;
        }
        return responseEntity;
    }

}
