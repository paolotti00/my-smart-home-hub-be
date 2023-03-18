package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.exception.NotExistException;
import com.paolotti.my.smart.home.mapper.IDeviceComponentMapper;
import com.paolotti.my.smart.home.mapper.deprecated.ILedMapper;
import com.paolotti.my.smart.home.model.DeviceComponent;
import com.paolotti.my.smart.home.model.DeviceComponentLight;
import com.paolotti.my.smart.home.repository.DeviceRepository;
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
    DeviceRepository deviceRepository;
    @Autowired
    IDeviceComponentMapper deviceComponentMapper;
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
                            updateComponentLightStatus(deviceEntity, deviceComponentMapper.toEntity((DeviceComponentLight) deviceComponent));
                        } catch (NotExistException e) {
                            // todo
                            e.printStackTrace();
                        }
                    case SENSOR_TEMPERATURE:
                        break;
                    case SENSOR_HUMIDITY:
                        break;
                }
            });
        }
    }
    private void updateComponentLightStatus(DeviceEntity deviceEntity, DeviceEntity.DeviceComponentEntityLight deviceComponentEntityLightUpdated) throws NotExistException {
        // todo think if should be Device model instead of deviceEntity
        logger.info("updating component light {} status of device {}",deviceComponentEntityLightUpdated.getId(),deviceEntity.getId());
        DeviceEntity.DeviceComponentEntityLight dComponentEntityLightToUpdate; // device component light to update
        /* todo understand how to assignee the id to component device (probably is better if is the iot device to choose and communicate)
            and in this flow if the component don't exist yet we will create it
            in this way we will have the component with component id sent by device*/
        Optional<DeviceEntity.DeviceComponentEntity> deviceComponentEntityOpt = deviceEntity.getComponents().stream().filter(deviceComponent -> deviceComponent.getId().equals(deviceComponentEntityLightUpdated.getId())).findFirst();
        DeviceEntity.DeviceComponentEntityLight deviceComponentLight;
        boolean componentAlreadyExist;
        if(!deviceComponentEntityOpt.isPresent()){
            // the component doesn't exist , will create
            logger.info("deviceComponentLight with id {} doesn't exist on device {}, will be created",deviceComponentEntityLightUpdated.getId(),deviceEntity.getId());
            deviceComponentLight = new DeviceEntity.DeviceComponentEntityLight();
            deviceComponentLight.setId(deviceComponentEntityLightUpdated.getId());
            componentAlreadyExist = false;
        } else {
            logger.info("deviceComponentLight with id {} already exist on device {}",deviceComponentEntityLightUpdated.getId(),deviceEntity.getId());
            deviceComponentLight = (DeviceEntity.DeviceComponentEntityLight) deviceComponentEntityOpt.get() ;
            componentAlreadyExist = true;
        }
        // general
        deviceComponentLight.setType(deviceComponentEntityLightUpdated.getType());
        deviceComponentLight.setAction(deviceComponentEntityLightUpdated.getAction());
        // leds
        deviceComponentLight.setLeds(deviceComponentEntityLightUpdated.getLeds());
        // saving
        if (!componentAlreadyExist){
            deviceEntity.getComponents().add(deviceComponentLight);
        }
        deviceEntity.setUpdateDate(LocalDateTime.now());
        deviceRepository.save(deviceEntity);
        logger.info("component light {} status of device {} correctly updated",deviceComponentEntityLightUpdated.getId(),deviceEntity.getId());
    }
    private void updateComponentSensorHeatStatus(DeviceEntity deviceEntity, DeviceComponent deviceComponent){
        logger.warn("updateComponentSensorHeatStatus is not implemented yet, so deviceComponent {} will not update",deviceComponent.getId());
    }
    private void updateComponentSensorHumidityStatus(DeviceEntity deviceEntity, DeviceComponent deviceComponent){
        logger.warn("updateComponentSensorHumidityStatus is not implemented yet, so deviceComponent {} will not update",deviceComponent.getId());
    }

}
