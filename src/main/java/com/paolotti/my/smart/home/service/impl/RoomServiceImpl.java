package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.mapper.IRoomMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.Room;
import com.paolotti.my.smart.home.repository.RoomRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.repository.entity.RoomEntity;
import com.paolotti.my.smart.home.service.IRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements IRoomService {
    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    IRoomMapper roomMapper;
    @Override
    public List<Room> getRoomsByUserId(String userId) throws ValidationException {

        logger.info("retrieving device for user with id {}", userId);
        List<Room> roomList = new ArrayList<>();
        if (StringUtils.isEmpty(userId)) {
            throw new ValidationException("userId cannot be null or empty");
        }
        Optional<List<RoomEntity>> roomEntityListOpt = roomRepository.findByUsersOwnersIdsContaining(userId);
        if (roomEntityListOpt.isPresent()) {
            List<RoomEntity> roomEntityList = roomEntityListOpt.get();
            roomList = roomMapper.toModelList(roomEntityList);
        } else {
            logger.warn("no devices found for user with id {}", userId);
        }

        logger.info("retrieved {} rooms for user with id {}", roomList.size(), userId);
        return roomList;
    }
}
