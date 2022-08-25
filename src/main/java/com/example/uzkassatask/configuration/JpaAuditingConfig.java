package com.example.uzkassatask.configuration;

import com.example.uzkassatask.entity.Employee;
import com.example.uzkassatask.repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class JpaAuditingConfig {

    private final EmployeeRepository userRepository;

    public JpaAuditingConfig(EmployeeRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    public AuditorAware<Employee> userAuditorAware(){
        return new AuditorAware<Employee>() {
            @Override
            public Optional<Employee> getCurrentAuditor() {
                return Optional.empty();
            }
        };
    }
}
