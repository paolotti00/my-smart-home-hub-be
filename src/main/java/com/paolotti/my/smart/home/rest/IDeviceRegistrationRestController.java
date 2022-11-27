package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.constant.RestConst;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import com.paolotti.my.smart.home.rest.dto.reqres.DeviceListResponseDto;
import com.paolotti.my.smart.home.rest.dto.reqres.DeviceRegistrationRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("registration/device/")
public interface IDeviceRegistrationRestController {
    @PostMapping("")
    ResponseEntity<DeviceDto> handleDeviceRegistrationRequest(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @RequestBody DeviceRegistrationRequestDto registrationRequestDto);

    @GetMapping("toactivate")
    ResponseEntity<DeviceListResponseDto> getDevicesToActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId);

    @PutMapping("/{deviceId}/activate")
    ResponseEntity<DeviceDto> deviceActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @PathVariable String deviceId);
}
