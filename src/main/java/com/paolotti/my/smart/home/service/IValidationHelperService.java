package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.model.ValidationHelperObject;

import java.util.ArrayList;

public interface IValidationHelperService {
    void validate(ArrayList<ValidationHelperObject> toValidate) throws  MissingFieldException;
}
