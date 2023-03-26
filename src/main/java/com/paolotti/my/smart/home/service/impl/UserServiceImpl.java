package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.exception.UserNotExistException;
import com.paolotti.my.smart.home.mapper.IUserMapper;
import com.paolotti.my.smart.home.model.User;
import com.paolotti.my.smart.home.repository.UserRepository;
import com.paolotti.my.smart.home.repository.entity.UserEntity;
import com.paolotti.my.smart.home.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    IUserMapper userMapper;

    @Override
    public User create(User userDto) {
        // todo pt
        return null;
    }

    @Override
    public User getUserById(String userId) throws UserNotExistException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: getting userId {}", methodName, userId);
        Optional<UserEntity> userEntityOpt = userRepository.findById(userId);
        if (!userEntityOpt.isPresent()) {
            logger.error("user userId {} not exist", userId);
            throw new UserNotExistException(userId,null);
        }
        UserEntity userEntity = userEntityOpt.get();
        User user = userMapper.toModel(userEntity);
        logger.info("{}: userId {} found, user ", methodName, user);
        return user;
    }

    @Override
    public User getUserByEmail(String userEmail) throws UserNotExistException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: getting user by mail  {}", methodName, userEmail);
        Optional<UserEntity> userEntityOpt = userRepository.findByEmail(userEmail);
        if (!userEntityOpt.isPresent()) {
            logger.error("user userId {} not exist", userEmail);
            throw new UserNotExistException(null, userEmail);
        }
        UserEntity userEntity = userEntityOpt.get();
        User user = userMapper.toModel(userEntity);
        logger.info("{}: userId {} found, user ", methodName, user);
        return user;
    }
}
