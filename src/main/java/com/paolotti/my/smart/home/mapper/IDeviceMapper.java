package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.constant.DeviceConst;
import com.paolotti.my.smart.home.enums.DeviceSensorTypeEnum;
import com.paolotti.my.smart.home.model.*;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.service.impl.DeviceServiceImpl;
import com.paolotti.my.smart.home.utility.CustomStringUtility;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface IDeviceMapper {
    static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    DeviceEntity toEntity(Device device);
    ArrayList<DeviceEntity> toEntities (ArrayList<Device> deviceList);
    Device toModel (DeviceEntity deviceEntity);
    ArrayList<Device> toModels (ArrayList<DeviceEntity> deviceList);

    default Device toDevice(DeviceRegistrationRequest deviceRegistrationRequest){
        logger.info("mapping registration request to device object");
        Device deviceToReturn = new Device();
        // device name
        if(deviceRegistrationRequest.getDeviceName()!=null){
            deviceToReturn.setName(deviceRegistrationRequest.getDeviceName());
        } else {
            deviceToReturn.setName(CustomStringUtility.generateRandomString());
        }
        // network data
        if (deviceRegistrationRequest.getNetworkData()!=null){
            NetworkData networkData = new NetworkData();
            // mac address
            if(deviceRegistrationRequest.getNetworkData().getMacAddress()!=null){
                networkData.setMacAddress(deviceRegistrationRequest.getNetworkData().getMacAddress());
            }
            // ip
            if(deviceRegistrationRequest.getNetworkData().getIp()!=null){
                networkData.setIp(deviceRegistrationRequest.getNetworkData().getIp());
            }
            // network name
            if(deviceRegistrationRequest.getNetworkData().getName()!=null){
                networkData.setName(deviceRegistrationRequest.getNetworkData().getName());
            }
            deviceToReturn.setNetworkData(networkData);
        }
        // user
        if(deviceRegistrationRequest.getUserId()!=null){
            User user = new User();
            user.setId(deviceRegistrationRequest.getUserId());
            deviceToReturn.setUser(user);
        }
        // device type
        if(deviceRegistrationRequest.getDeviceType()!=null){
            deviceToReturn.setType(deviceRegistrationRequest.getDeviceType());
        }
        // setting number of sensor
        // lights
        if(deviceRegistrationRequest.getNumOfLight()!=null && deviceRegistrationRequest.getNumOfLight()>0){
            deviceToReturn.setLightList(new ArrayList<>());
            for(int i=0; i<deviceRegistrationRequest.getNumOfLight();i++){
                DeviceComponentLight deviceLight = new DeviceComponentLight();
                deviceLight.setId(DeviceConst.ID_PREFIX_LIGHT+i);
                deviceToReturn.getLightList().add(deviceLight);
            }
        }
        // humidity sensors
        deviceToReturn.setSensorList(initializeNewSensor(deviceRegistrationRequest.getNumOfHumiditySensors(),DeviceSensorTypeEnum.HUMIDITY,DeviceConst.ID_PREFIX_SENSOR_HUMIDITY,deviceToReturn.getSensorList()));
        // heat sensors
        deviceToReturn.setSensorList(initializeNewSensor(deviceRegistrationRequest.getNumOfHumiditySensors(),DeviceSensorTypeEnum.HEAT,DeviceConst.ID_PREFIX_SENSOR_HEATH,deviceToReturn.getSensorList()));
        logger.info("registration request mapped to device object {}",deviceToReturn);
        return deviceToReturn;
    }

    default ArrayList<DeviceComponentSensor> initializeNewSensor(Integer numOfSensor, DeviceSensorTypeEnum deviceSensorTypeEnum, String SensorIdPrefix, ArrayList<DeviceComponentSensor>currentSensorList){
        int addedSensorCount = 0;
        if(numOfSensor!=null && numOfSensor>0) {
            if (currentSensorList == null) {
                currentSensorList = new ArrayList<>();
            }
            for(int i=0; i<numOfSensor;i++){
                DeviceComponentSensor deviceSensor = new DeviceComponentSensor();
                deviceSensor.setType(deviceSensorTypeEnum);
                deviceSensor.setId(SensorIdPrefix+i);
                currentSensorList.add(deviceSensor);
            }
        }
        logger.info("created {} sensor of type {}. {} sensor expected",addedSensorCount,deviceSensorTypeEnum,numOfSensor);
        return currentSensorList;
    }
}
