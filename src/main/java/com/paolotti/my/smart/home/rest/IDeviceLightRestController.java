package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.rest.dto.reqres.BaseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("light")
public interface IDeviceLightRestController {
    @PostMapping("device/{deviceId}/switch/on")
    ResponseEntity<BaseResponseDto> switchOnAllLightsByDevice(@PathVariable String deviceId);
    @PostMapping("device/{deviceId}/switch/off")
    ResponseEntity<BaseResponseDto> switchOffAllLightsByDevice(@PathVariable String deviceId);
    @PostMapping("group/{groupId}/switch/on")
    ResponseEntity<BaseResponseDto> switchOnAllLightsByGroup(@PathVariable String groupId);
    @PostMapping("group/{groupId}/switch/off")
    ResponseEntity<BaseResponseDto> switchOffAllLightsByGroup(@PathVariable String groupId);
}
