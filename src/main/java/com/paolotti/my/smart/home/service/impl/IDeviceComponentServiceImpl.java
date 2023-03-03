package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.NotExistException;
import com.paolotti.my.smart.home.mapper.IDeviceComponentLightMapper;
import com.paolotti.my.smart.home.mapper.ILedMapper;
import com.paolotti.my.smart.home.model.DeviceComponent;
import com.paolotti.my.smart.home.model.DeviceComponentLight;
import com.paolotti.my.smart.home.repository.IDeviceRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.service.IDeviceComponentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class IDeviceComponentServiceImpl implements IDeviceComponentService {
    private static final Logger logger = LoggerFactory.getLogger(IDeviceComponentServiceImpl.class);
    @Autowired
    IDeviceRepository deviceRepository;
    @Autowired
    IDeviceComponentLightMapper deviceComponentLightMapper;
    @Autowired
    ILedMapper ledMapper;

    @Override
    public void updateComponentsStatus(DeviceEntity deviceEntity, ArrayList<DeviceComponent> componentsToBeUpdate){

        if(componentsToBeUpdate==null){
            logger.info("componentsToBeUpdate is null");
        } else {
            logger.info("there are {} components to update",componentsToBeUpdate.size());
            componentsToBeUpdate.forEach(deviceComponent -> {
                switch (deviceComponent.getType()){
                    case LIGHT:
                        try {
                            updateComponentLightStatus(deviceEntity, deviceComponentLightMapper.toEntity((DeviceComponentLight) deviceComponent));
                        } catch (NotExistException e) {
                            // todo
                            e.printStackTrace();
                        }
                    case SENSOR_HEAT:
                        break;
                    case SENSOR_HUMIDITY:
                        break;
                }
            });
        }
    }
    private void updateComponentLightStatus(DeviceEntity deviceEntity, DeviceEntity.DeviceComponentEntityLight deviceComponentEntityLight) throws NotExistException {
        // todo think if should be Device model instead of deviceEntity
        logger.info("updating component light {} status of device {}",deviceComponentEntityLight.getId(),deviceEntity.getId());
        DeviceEntity.DeviceComponentEntityLight dComponentEntityLightToUpdate; // device component light to update
        /* todo understand how to assignee the id to component device (probably is better if is the iot device to choose and communicate)
            and in this flow if the component don't exist yet we will create it
            in this way we will have the component with component id sent by device*/
        Optional<DeviceEntity.DeviceComponentEntity> deviceComponentEntityOpt = deviceEntity.getComponents().stream().filter(deviceComponent -> deviceComponent.getId().equals(deviceComponentEntityLight.getId())).findFirst();
        if(!deviceComponentEntityOpt.isPresent()){
            // todo if not exist we have to create it
            throw new NotExistException(String.format("device component id %s not exist in device id %s",deviceComponentEntityLight.getId(),deviceEntity.getId()));
        }
        dComponentEntityLightToUpdate = (DeviceEntity.DeviceComponentEntityLight) deviceComponentEntityOpt.get() ;
        // leds
        dComponentEntityLightToUpdate.setLeds(deviceComponentEntityLight.getLeds());
        dComponentEntityLightToUpdate.setAction(deviceComponentEntityLight.getAction());
        deviceEntity.setUpdateDate(LocalDateTime.now());
        deviceRepository.save(deviceEntity);
        logger.info("component light {} status of device {} correctly updated",deviceComponentEntityLight.getId(),deviceEntity.getId());
    }
    private void updateComponentSensorHeatStatus(DeviceEntity deviceEntity, DeviceComponent deviceComponent){
        logger.warn("updateComponentSensorHeatStatus is not implemented yet, so deviceComponent {} will not update",deviceComponent.getId());
    }
    private void updateComponentSensorHumidityStatus(DeviceEntity deviceEntity, DeviceComponent deviceComponent){
        logger.warn("updateComponentSensorHumidityStatus is not implemented yet, so deviceComponent {} will not update",deviceComponent.getId());
    }

}
