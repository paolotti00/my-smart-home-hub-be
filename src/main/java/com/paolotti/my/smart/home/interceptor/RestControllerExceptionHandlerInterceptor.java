package com.paolotti.my.smart.home.interceptor;

import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.dto.rest.BaseResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerExceptionHandlerInterceptor extends ResponseEntityExceptionHandler { // todo pt add exception handler
    private static final Logger logger = LoggerFactory.getLogger(RestControllerExceptionHandlerInterceptor.class);

    @ExceptionHandler(value = {MissingFieldException.class})
    protected ResponseEntity<Object> handleValidationExceptions(Exception ex, WebRequest request){
        logger.warn("handling {} exception",ex.getClass());
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        baseResponseDto.setErrorCode("XXX");
        baseResponseDto.setMessage(ex.getMessage());
        logger.warn("response {}",baseResponseDto);
        ex.printStackTrace();
        return handleExceptionInternal(ex,baseResponseDto,new HttpHeaders(), HttpStatus.BAD_REQUEST,request);
    }
    @ExceptionHandler(value = {
            BrandNotSupportedException.class,
            DeviceAlreadyActivated.class,
            DeviceAlreadyRegisteredException.class,
            DeviceNotExistsException.class,
            DeviceWrongStatusException.class,
            GroupNotExistsException.class,
            UserNotExistException.class
    })
    protected ResponseEntity<Object> handleManagedErrorsExceptions(Exception ex, WebRequest request){
        logger.warn("handling {} exception",ex.getClass());
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        baseResponseDto.setErrorCode("XXX");
        baseResponseDto.setMessage(ex.getMessage());
        logger.warn("response {}",baseResponseDto);
        ex.printStackTrace();
        return handleExceptionInternal(ex,baseResponseDto,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,request);
    }
    @ExceptionHandler(value = {
            GenericException.class
    })
    protected ResponseEntity<Object> handleGenericErrorsExceptions(Exception ex, WebRequest request){
        logger.warn("handling {} exception",ex.getClass());
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        baseResponseDto.setErrorCode("XXX");
        baseResponseDto.setMessage("generic error");
        logger.warn("response {}",baseResponseDto.toString());
        ex.printStackTrace();
        return handleExceptionInternal(ex,baseResponseDto,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,request);
    }
}
