package com.example.uzkassatask.service;

import com.example.uzkassatask.dto.EmpDto;
import com.example.uzkassatask.entity.Employee;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Set;

public interface EmployeeService {

    boolean activate(long id);
    Employee edit(EmpDto emp, long id, long companyId) throws NotFound;
    Employee get(long id, long companyId);
    Set<Employee> getAll(long companyId);
    boolean delete(long id, long companyId) throws NotFound;
}
