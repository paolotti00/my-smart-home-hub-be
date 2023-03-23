package com.paolotti.my.smart.home.service.deprecated;

import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.model.deprecated.Room;

import java.util.List;

public interface IRoomService {
   List<Room> getRoomsByUserId(String userId) throws ValidationException;
}
