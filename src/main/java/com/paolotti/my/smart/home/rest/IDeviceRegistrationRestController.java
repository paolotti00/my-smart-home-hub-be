package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.constant.RestConst;
import com.paolotti.my.smart.home.rest.dto.reqres.ActivateDeviceResponseDto;
import com.paolotti.my.smart.home.rest.dto.reqres.DeviceRegistrationRequestDto;
import com.paolotti.my.smart.home.rest.dto.reqres.DeviceRegistrationResponseDto;
import com.paolotti.my.smart.home.rest.dto.reqres.GetDevicesToActivateResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("registration/device/")
public interface IDeviceRegistrationRestController {
    @PostMapping("")
    ResponseEntity<DeviceRegistrationResponseDto> handleDeviceRegistrationRequest(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @RequestBody DeviceRegistrationRequestDto registrationRequestDto);

    @GetMapping("toactivate")
    ResponseEntity<GetDevicesToActivateResponseDto> getDevicesToActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId);

    @PutMapping("/{deviceId}/activate")
    ResponseEntity<ActivateDeviceResponseDto> deviceActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @PathVariable String deviceId);
}
