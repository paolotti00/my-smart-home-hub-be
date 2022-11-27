package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("device")
public interface IDeviceRestController {
    @PostMapping("")
    DeviceDto create (DeviceDto deviceDto);
}
