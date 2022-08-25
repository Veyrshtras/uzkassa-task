package com.example.uzkassatask.repository;

import com.example.uzkassatask.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee>{

    Optional<Employee> findByUsernameAndPassword(String username, String password);
    Optional<Employee> findByEmail(String email);
}
