package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import com.paolotti.my.smart.home.dto.rest.MeasurementDto;
import com.paolotti.my.smart.home.enums.MeasurementTypeEnum;
import com.paolotti.my.smart.home.exception.GenericException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("rooms")
@Tag(name = "room")
public interface IRoomRestController {
    @Tag(name = "room")
    @GetMapping("{roomId}/devices")
    ResponseEntity<BaseResponseDto<List<DeviceDto>>> getDevices(@PathVariable String roomId) throws GenericException;

    @Tag(name = "room")
    @GetMapping("{roomId}/measurement")
    ResponseEntity<BaseResponseDto<List<MeasurementDto>>> getMeasurementByRoomId(@PathVariable String roomId,
                                                                                 @RequestParam(required = false) MeasurementTypeEnum type,
                                                                                 @RequestParam(required = false) LocalDateTime from,
                                                                                 @RequestParam(required = false) LocalDateTime to) throws GenericException;


}
