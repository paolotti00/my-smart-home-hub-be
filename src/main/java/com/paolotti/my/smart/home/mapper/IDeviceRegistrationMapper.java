package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.DeviceRegistrationRequest;
import com.paolotti.my.smart.home.model.DeviceRegistrationResponse;
import com.paolotti.my.smart.home.rest.dto.reqres.DeviceRegistrationRequestDto;
import com.paolotti.my.smart.home.rest.dto.reqres.DeviceRegistrationResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDeviceRegistrationMapper {
    DeviceRegistrationRequestDto toDeviceRegistrationRequestDto(DeviceRegistrationRequest deviceRegistrationRequest);
    DeviceRegistrationRequest toDeviceRegistrationRequest(DeviceRegistrationRequestDto deviceRegistrationRequest);

    DeviceRegistrationResponseDto toDeviceRegistrationResponseDto(DeviceRegistrationResponse deviceRegistrationResponse);
    DeviceRegistrationResponse toDeviceRegistrationRequest(DeviceRegistrationResponseDto deviceRegistrationResponseDto);
}
