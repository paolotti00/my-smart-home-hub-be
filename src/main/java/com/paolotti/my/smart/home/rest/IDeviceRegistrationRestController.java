package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.constant.RestConst;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import com.paolotti.my.smart.home.rest.dto.reqres.DeviceRegistrationRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("registration/device/")
public interface IDeviceRegistrationRestController {
    @PostMapping("")
    ResponseEntity<DeviceDto> handleDeviceRegistrationRequest(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @RequestBody DeviceRegistrationRequestDto registrationRequestDto) throws DeviceAlreadyRegisteredException, MissingFieldException, DeviceCreationException, UserNotExistException;

    @GetMapping("toactivate")
    ResponseEntity<ArrayList<DeviceDto>> getDevicesToActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId) throws MissingFieldException, UserNotExistException;

    @PutMapping("/{deviceId}/activate")
    ResponseEntity<DeviceDto> deviceActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @PathVariable String deviceId) throws DeviceWrongStatusException, DeviceNotExistsException, MissingFieldException, DeviceAlreadyActivated, UserNotExistException;
}
