package com.example.uzkassatask.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpModel {
    private String empName;
    private String empUsername;
    private String empPassword;
    private String empEmail;
    private String empAddress;
    private String empPhoneNumber;
    private String companyName;
    private String companyAddress;
    private long companyZipCode;
}
