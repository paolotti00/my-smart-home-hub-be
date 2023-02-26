package com.paolotti.my.smart.home.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.rest.dto.BaseResponseDto;
import com.paolotti.my.smart.home.rest.dto.ColorRgbDto;
import com.paolotti.my.smart.home.mqtt.dto.ActionDto;
import com.paolotti.my.smart.home.rest.dto.view.JsonViewConfig;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("device")
public interface IDeviceRestController {
    @PostMapping("")
    @JsonView(JsonViewConfig.HighDetail.class)
    DeviceDto create (@JsonView(JsonViewConfig.LowDetail.class) @RequestBody DeviceDto deviceDto) throws MissingFieldException;
    @PostMapping("{deviceId}/light/switch/on")
    ResponseEntity<BaseResponseDto> switchOnAllLightsByDevice(@PathVariable String deviceId) throws DeviceNotExistsException, BrandNotSupportedException;
    @PostMapping("{deviceId}/light/switch/off")
    ResponseEntity<BaseResponseDto> switchOffAllLights(@PathVariable String deviceId) throws DeviceNotExistsException, BrandNotSupportedException;
    @PutMapping("{deviceId}/set/color")
    ResponseEntity<BaseResponseDto> setColor(@PathVariable String deviceId, @RequestBody ColorRgbDto colorRgbDto) throws DeviceNotExistsException, BrandNotSupportedException, GenericException;
    @PutMapping("{deviceId}/do/action")
    ResponseEntity<BaseResponseDto> doAction(@PathVariable String deviceId, @RequestBody ActionDto actionDto) throws DeviceNotExistsException, BrandNotSupportedException, GenericException;
}
