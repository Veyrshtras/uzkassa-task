package com.example.uzkassatask.dto;

import com.example.uzkassatask.entity.Company;
import com.example.uzkassatask.entity.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpDto {

    private String name;
    private String username;
    private String password;
    private String email;
    private Company company;

}
