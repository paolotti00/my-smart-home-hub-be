package com.paolotti.my.smart.home.service.deprecated.impl;

import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.model.deprecated.ValidationHelperObject;
import com.paolotti.my.smart.home.service.deprecated.IValidationHelperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ValidationImplService implements IValidationHelperService {
    private static final Logger logger = LoggerFactory.getLogger(ValidationImplService.class);
    @Override
    public void validate(ArrayList<ValidationHelperObject> toValidate) throws MissingFieldException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: starting to validate {}",methodName,toValidate);

        final boolean[] mandatoryFieldValid = {true};
        final String[] mandatoryFieldMessage = new String[1];
        toValidate.forEach(itemToValidate -> {
            switch (itemToValidate.getValidationType()){
                case NOT_NULL:{
                    if (itemToValidate.getValue()==null){
                        mandatoryFieldValid[0] =false;
                        itemToValidate.setValid(false);
                        if (mandatoryFieldMessage[0]==null){
                            mandatoryFieldMessage[0]= String.format("the field '%s' is mandatory, instead this is %s",itemToValidate.getFieldName(),itemToValidate.getValue());
                        }else {
                            mandatoryFieldMessage[0] = mandatoryFieldMessage[0] + " || " + String.format("the field '%s' is mandatory, instead this is %s.",itemToValidate.getFieldName(),itemToValidate.getValue());;
                        }
                    }
                    break;
                }
            }
        });
        // check before the mandatory fields
        if (mandatoryFieldValid[0] != true){
            logger.warn("validation failed");
            throw new MissingFieldException(mandatoryFieldMessage[0]);
        } else {
            logger.info("validation done");
        }
    }
}
