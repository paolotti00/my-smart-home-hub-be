package com.paolotti.my.smart.home.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.dto.ActionDto;
import com.paolotti.my.smart.home.dto.rest.view.JsonViewConfig;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("devices")
@Tag(name = "device")
public interface IDeviceRestController {
    @PostMapping("")
    @Tag(name = "device")
    @JsonView(JsonViewConfig.HighDetail.class)
    DeviceDto create (@JsonView(JsonViewConfig.LowDetail.class) @RequestBody DeviceDto deviceDto) throws MissingFieldException;
    @GetMapping("{deviceId}")
    @Tag(name = "device")
    ResponseEntity<BaseResponseDto<DeviceDto>>getDevice(@PathVariable String deviceId);
    @PostMapping("{deviceId}/light/switch/on")
    @Tag(name = "device")
    ResponseEntity<BaseResponseDto<?>> switchOnAllLightsByDevice(@PathVariable String deviceId) throws DeviceNotExistsException, BrandNotSupportedException;
    @PostMapping("{deviceId}/light/switch/off")
    @Tag(name = "device")
    ResponseEntity<BaseResponseDto<?>> switchOffAllLights(@PathVariable String deviceId) throws DeviceNotExistsException, BrandNotSupportedException;
    @PutMapping("{deviceId}/set/color")
    @Tag(name = "device")
    ResponseEntity<BaseResponseDto<?>> setColor(@PathVariable String deviceId, @RequestBody String rgbColor) throws DeviceNotExistsException, BrandNotSupportedException, GenericException;

    @GetMapping("{deviceId}/actions")
    @Tag(name = "device")
    ResponseEntity<BaseResponseDto<List<ActionDto>>> getSupportedActions(@PathVariable  String deviceId) throws DeviceNotExistsException,GenericException;

    @PutMapping("{deviceId}/do/action")
    @Tag(name = "device")
    ResponseEntity<BaseResponseDto<?>> doAction(@PathVariable String deviceId, @RequestBody ActionDto actionDto) throws DeviceNotExistsException, BrandNotSupportedException, GenericException;
}
