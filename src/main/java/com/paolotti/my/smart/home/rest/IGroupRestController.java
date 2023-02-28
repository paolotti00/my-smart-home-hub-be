package com.paolotti.my.smart.home.rest;

import com.paolotti.my.smart.home.exception.GroupNotExistsException;
import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("group")
public interface IGroupRestController {
    @PostMapping("{groupId}/switch/on")
    ResponseEntity<BaseResponseDto> switchOnAllLightsByGroup(@PathVariable String groupId) throws GroupNotExistsException;
    @PostMapping("{groupId}/switch/off")
    ResponseEntity<BaseResponseDto> switchOffAllLightsByGroup(@PathVariable String groupId) throws GroupNotExistsException;
}
