package com.paolotti.my.smart.home.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.dto.ExtraActionCommandDataDto;
import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.dto.rest.view.JsonViewConfig;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.MissingFieldException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("devices")
@Tag(name = "device")
public interface IDeviceRestController {
    @PostMapping("")
    @Tag(name = "device")
    @JsonView(JsonViewConfig.HighDetail.class)
    DeviceDto create (@JsonView(JsonViewConfig.LowDetail.class) @RequestBody DeviceDto deviceDto) throws GenericException;
    @GetMapping("{deviceId}")
    @Tag(name = "device")
    ResponseEntity<BaseResponseDto<DeviceDto>>getDevice(@PathVariable String deviceId);
    @PutMapping("{deviceId}/light/switch/{onOffStatus}")

    // light
    @Tag(name = "device")
    ResponseEntity<BaseResponseDto<?>> switchLights(@PathVariable String deviceId, @PathVariable OnOffStatusEnum onOffStatus) throws GenericException;
    @PostMapping("{deviceId}/color")
    @Tag(name = "device")    ResponseEntity<BaseResponseDto<?>> setLightColor(@PathVariable String deviceId, @RequestBody List<Integer> colorRgbAndIntensity) throws DeviceNotExistsException, BrandNotSupportedException, GenericException;

    // action
    @GetMapping("{deviceId}/actions")
    @Tag(name = "device")
    ResponseEntity<BaseResponseDto<List<ExtraActionCommandDataDto>>> getSupportedActions(@PathVariable  String deviceId) throws DeviceNotExistsException,GenericException;

    @PostMapping("{deviceId}/action")
    @Tag(name = "device")
    ResponseEntity<BaseResponseDto<?>> doAction(@PathVariable String deviceId, @RequestBody ExtraActionCommandDataDto extraActionCommandDataDto) throws DeviceNotExistsException, BrandNotSupportedException, GenericException;
}
