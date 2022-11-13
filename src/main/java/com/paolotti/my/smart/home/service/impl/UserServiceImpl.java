package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.exception.UserNotExistException;
import com.paolotti.my.smart.home.mapper.IUserMapper;
import com.paolotti.my.smart.home.model.User;
import com.paolotti.my.smart.home.repository.IUserCustomRepository;
import com.paolotti.my.smart.home.repository.entity.UserEntity;
import com.paolotti.my.smart.home.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    IUserCustomRepository userCustomRepository;
    @Autowired
    IUserMapper userMapper;

    @Override
    public User getUserById(String userId) throws UserNotExistException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: getting userId {}",methodName,userId);
        UserEntity userEntity = userCustomRepository.getUserById(userId);
        if(userEntity==null){
            logger.error("user userId {} not exist",userId);
            throw new UserNotExistException(userId);
        }
        User user = userMapper.toModel(userEntity);
        logger.info("{}: userId {} found, user ",methodName,user);
        return user;
    }
    @Override
    public User checkIfUserExistsAndRetrieve(String userId) throws UserNotExistException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: checking if user exist userId {}",methodName,userId);
        User user = getUserById(userId);
        if(user==null){
            throw new UserNotExistException(userId);
        }
        logger.info("{}: user userId {} exist",methodName,userId);
        return user;
    }
}
