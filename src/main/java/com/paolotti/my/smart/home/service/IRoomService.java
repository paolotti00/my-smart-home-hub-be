package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.model.Room;

import java.util.List;

public interface IRoomService {
   List<Room> getRoomsByUserId(String userId) throws GenericException;
}
