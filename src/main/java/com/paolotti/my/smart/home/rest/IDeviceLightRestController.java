package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GroupNotExistsException;
import com.paolotti.my.smart.home.rest.dto.BaseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("light")
public interface IDeviceLightRestController {
    @PostMapping("device/{deviceId}/switch/on")
    ResponseEntity<BaseResponseDto> switchOnAllLightsByDevice(@PathVariable String deviceId) throws DeviceNotExistsException, BrandNotSupportedException;
    @PostMapping("device/{deviceId}/switch/off")
    ResponseEntity<BaseResponseDto> switchOffAllLightsByDevice(@PathVariable String deviceId) throws DeviceNotExistsException, BrandNotSupportedException;
    @PostMapping("group/{groupId}/switch/on")
    ResponseEntity<BaseResponseDto> switchOnAllLightsByGroup(@PathVariable String groupId) throws GroupNotExistsException;
    @PostMapping("group/{groupId}/switch/off")
    ResponseEntity<BaseResponseDto> switchOffAllLightsByGroup(@PathVariable String groupId) throws GroupNotExistsException;
}
