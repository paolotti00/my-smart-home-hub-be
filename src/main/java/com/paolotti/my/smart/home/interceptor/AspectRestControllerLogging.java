package com.paolotti.my.smart.home.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.rest.impl.UserRestControllerImplRestControllerExceptionHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Aspect
@Component
public class AspectRestControllerLogging {

    private static final Logger logger = LoggerFactory.getLogger(AspectRestControllerLogging.class);

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        // log request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        logger.info("Request: [{} {}] from IP: {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());

        // log request body
        Object requestBody = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg != null && !(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse))
                .findFirst().orElse(null);
        if (requestBody != null) {
            logger.info("Request body: {}", new ObjectMapper().writeValueAsString(requestBody));
        }

        // execute method and log response
        Object result = joinPoint.proceed();
        if (result instanceof ResponseEntity) {
            ResponseEntity responseEntity = (ResponseEntity) result;
            logger.info("Response status: {}", responseEntity.getStatusCodeValue());

            // log response body
            Object responseBody = responseEntity.getBody();
            if (responseBody != null) {
                logger.info("Response body: {}", responseBody);
            }
        }

        return result;
    }
}
