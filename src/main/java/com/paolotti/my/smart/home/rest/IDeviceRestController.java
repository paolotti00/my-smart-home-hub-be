package com.paolotti.my.smart.home.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.rest.dto.view.JsonViewConfig;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("device")
public interface IDeviceRestController {
    @PostMapping("")
    @JsonView(JsonViewConfig.HighDetail.class)
    DeviceDto create (@JsonView(JsonViewConfig.LowDetail.class) @RequestBody DeviceDto deviceDto) throws MissingFieldException;
}
