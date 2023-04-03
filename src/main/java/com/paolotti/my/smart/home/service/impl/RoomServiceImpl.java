package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.SensorTypeEnum;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.RoomNotExistsException;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.factory.IBeanFactoryDeviceService;
import com.paolotti.my.smart.home.mapper.IRoomMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.Room;
import com.paolotti.my.smart.home.model.Sensor;
import com.paolotti.my.smart.home.repository.RoomRepository;
import com.paolotti.my.smart.home.repository.entity.RoomEntity;
import com.paolotti.my.smart.home.service.IDeviceService;
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
    @Autowired
    IBeanFactoryDeviceService beanFactoryDeviceService;
    @Override
    public List<Room> getRoomsByUserId(String userId) throws GenericException {
        logger.info("retrieving device for user with id {}", userId);
        List<Room> roomList = new ArrayList<>();
        IDeviceService deviceService = beanFactoryDeviceService.getDeviceServiceByBrand(DeviceBrandEnum.NO_BRAND);
        if (StringUtils.isEmpty(userId)) {
            throw new ValidationException("userId cannot be null or empty");
        }
        Optional<List<RoomEntity>> roomEntityListOpt = roomRepository.findByUsersOwnersIdsContaining(userId);
        if (roomEntityListOpt.isPresent()) {
            List<RoomEntity> roomEntityList = roomEntityListOpt.get();
            roomList = roomMapper.toModelList(roomEntityList);
            logger.debug("calculating tempo / hum / light of rooms");
            roomList.forEach(room -> {
                try {
                    logger.debug("calculating temp / hum / light of room with id {}",room.getId());
                    List<Device> deviceList = deviceService.getDevicesByRoomId(room.getId()) ;
                    double tempTot=0;
                    int tempN = 0;
                    double humTot=0;
                    int humN = 0;
                    room.setHaveLights(false);
                    for(Device device:deviceList){
                        for(Sensor sensor: device.getSensors()){
                            // tot of temp
                            if(sensor.getType() == SensorTypeEnum.TEMPERATURE){
                                tempTot = tempTot + sensor.getValue();
                                tempN = tempN+1;
                            }
                            // tot of humidity
                            if(sensor.getType() == SensorTypeEnum.HUMIDITY){
                                humTot = humTot + sensor.getValue();
                                humN = humN+1;
                            }
                        }
                        // lights
                        if (device.getLeds()!=null){
                            room.setHaveLights(true);
                        }
                    }
                    room.setTemp(Math.round(((tempTot/tempN) * 100)/100)); // average rounded to 2 decimal
                    room.setHumidity(Math.round(((humTot/humN) * 100)/100)); // average rounded to 2 decimal
                } catch (RoomNotExistsException | ValidationException e) {
                   logger.warn("room {} doesn't exist",room.getId());
                }
            });
        } else {
            logger.warn("no devices found for user with id {}", userId);
        }

        logger.info("retrieved {} rooms for user with id {}", roomList.size(), userId);
        return roomList;
    }
}
