package com.example.uzkassatask.service;

import com.example.uzkassatask.model.ResponseCurrencyRate;
import com.example.uzkassatask.model.SignInModel;
import com.example.uzkassatask.model.SignUpModel;

public interface OtherServices {

    String signUp(SignUpModel signUpModel) throws Exception;
    String  singIn(SignInModel signInModel);
    ResponseCurrencyRate getCurrencyRateApiByCode(String code);
}
