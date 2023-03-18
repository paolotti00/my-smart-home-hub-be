package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.exception.ValidationException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("rooms")
@Tag(name = "room")
public interface IRoomRestController {
    @Tag(name = "user")
    @GetMapping("{roomId}/devices")
    ResponseEntity<BaseResponseDto<List<DeviceDto>>> getDevices(@PathVariable String roomId) throws ValidationException;

}
