package com.paolotti.my.smart.home.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.config.JsonViewConfig;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("device")
public interface IDeviceRestController {
    @PostMapping("")
    @JsonView(JsonViewConfig.AsOutput.class)
    DeviceDto create (@JsonView(JsonViewConfig.AsInput.class) @RequestBody DeviceDto deviceDto);
}
