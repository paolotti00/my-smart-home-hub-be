package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.DeviceRegistrationRequest;
import com.paolotti.my.smart.home.rest.dto.reqres.DeviceRegistrationRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDeviceRegistrationMapper extends IBaseMapper{
    DeviceRegistrationRequestDto toDeviceRegistrationRequestDto(DeviceRegistrationRequest deviceRegistrationRequest);
    DeviceRegistrationRequest toDeviceRegistrationRequest(DeviceRegistrationRequestDto deviceRegistrationRequest);
}
