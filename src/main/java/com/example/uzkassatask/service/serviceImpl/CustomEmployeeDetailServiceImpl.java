package com.example.uzkassatask.service.serviceImpl;

import com.example.uzkassatask.entity.Employee;
import com.example.uzkassatask.model.CustomEmployeeDetail;
import com.example.uzkassatask.repository.EmployeeRepository;
import com.example.uzkassatask.service.CustomEmployeeDetailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomEmployeeDetailServiceImpl implements CustomEmployeeDetailService {

    private final EmployeeRepository employeeRepository;

    public CustomEmployeeDetailServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> user=employeeRepository.findByEmail(username);
        return user.map(CustomEmployeeDetail::new)
                .orElse(null);
    }
}
