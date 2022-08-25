package com.example.uzkassatask.service;

import com.example.uzkassatask.dto.CompDto;
import com.example.uzkassatask.entity.Company;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public interface CompanyService {

    Company editCompany(CompDto compDto, long id) throws NotFound;
    Company blockCompany(long id) throws NotFound;
}
