package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.DeviceRegistrationRequest;
import com.paolotti.my.smart.home.model.DeviceRegistrationResponse;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationRequestDto;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface IDeviceRegistrationMapper {
    DeviceRegistrationRequestDto toDeviceRegistrationRequestDto(DeviceRegistrationRequest deviceRegistrationRequest);
    DeviceRegistrationRequest toDeviceRegistrationRequest(DeviceRegistrationRequestDto deviceRegistrationRequest);

    DeviceRegistrationResponseDto toDeviceRegistrationResponseDto(DeviceRegistrationResponse deviceRegistrationResponse);
    DeviceRegistrationResponse toDeviceRegistrationRequest(DeviceRegistrationResponseDto deviceRegistrationResponseDto);
}
