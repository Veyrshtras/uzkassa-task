package com.example.uzkassatask;

import com.example.uzkassatask.entity.Employee;
import com.example.uzkassatask.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class EmployeeTest {

    @Autowired
    private EmployeeService service;

    private Set<Employee> employees(){
        Set<Employee> employeeSet=new HashSet<>();
        return employeeSet;
    }


    @Test
    public void getAllTest(){
        Set<Employee> actual=service.getAll(1);
        Set<Employee> expected=employees();
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void getTest(){
        Employee actual=service.get(1,1);
        Optional<Employee> expected=employees().stream().filter(employee -> employee.getId()==1).findAny();
        expected.ifPresent(employee -> Assertions.assertEquals(actual, employee));
    }

    @Test
    public void deleteTest() throws NotFound {
        boolean actual=service.delete(1,1);
        Optional<Employee> expected=employees().stream().filter(employee -> employee.getId()==1).findAny();
        boolean test=false;
        if (expected.isPresent())
            test=employees().remove(expected.get());
        boolean finalTest = test;
        expected.ifPresent(employee -> Assertions.assertEquals(actual, finalTest));
    }

}
