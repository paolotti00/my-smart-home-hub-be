package com.paolotti.my.smart.home.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.constant.RestConst;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.dto.rest.view.JsonViewConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("registration/device/")
public interface IDeviceRegistrationRestController {
    @PostMapping("")
    @JsonView(JsonViewConfig.HighDetail.class)
    ResponseEntity<DeviceDto> handleDeviceRegistrationRequest(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @JsonView(JsonViewConfig.MediumDetail.class) @RequestBody DeviceDto registrationRequestDto) throws DeviceAlreadyRegisteredException, MissingFieldException, DeviceCreationException, UserNotExistException;

    @GetMapping("toactivate")
    @JsonView(JsonViewConfig.HighDetail.class)
    ResponseEntity<List<DeviceDto>> getDevicesToActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId) throws MissingFieldException, UserNotExistException;

    @PutMapping("/{deviceId}/activate")
    @JsonView(JsonViewConfig.HighDetail.class)
    ResponseEntity<DeviceDto> deviceActivate(@RequestHeader(RestConst.HEADER_USER_ID) String userId, @PathVariable String deviceId) throws DeviceWrongStatusException, DeviceNotExistsException, MissingFieldException, DeviceAlreadyActivated, UserNotExistException;
}
