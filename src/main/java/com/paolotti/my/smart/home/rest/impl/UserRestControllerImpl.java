package com.paolotti.my.smart.home.rest.impl;

import com.paolotti.my.smart.home.rest.IUserRestController;
import com.paolotti.my.smart.home.rest.dto.UserDto;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserRestControllerImpl extends AbstractBaseRestController implements IUserRestController {
    @Override
    public UserDto create(UserDto userDto) {
        return null;
    }
}
