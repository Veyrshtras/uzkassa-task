package com.example.uzkassatask.service.serviceImpl;

import com.example.uzkassatask.dto.EmpDto;
import com.example.uzkassatask.entity.Employee;
import com.example.uzkassatask.repository.EmployeeRepository;
import com.example.uzkassatask.service.EmployeeService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean activate(long id) {
        repository.findById(id).get().setStatus(true);
        return true;
    }

    @Override
    public Employee edit(EmpDto emp, long id, long companyId) throws NotFound {
        Optional<Employee> employee=repository.findById(id);
        if (employee.isPresent()){
            employee.get().setUsername(emp.getUsername());
            employee.get().setPassword(emp.getPassword());
            employee.get().setEmail(emp.getEmail());
            employee.get().setName(emp.getName());

            return employee.get();
        }
        else throw new NotFound();
    }

    @Override
    @Transactional
    public Employee get(long id, long companyId) {

        Optional<Employee> employee=repository.findById(id);
        if (employee.isPresent())
            return employee.get();
        else
            return null;
    }

    @Override
    public Set<Employee> getAll(long companyId) {
        return repository.findAll()
                .stream()
                .filter(employee -> employee.getCompany().getId()==companyId)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean delete(long id, long companyId) throws NotFound {

        Optional<Employee> employee=repository.findById(id);
        if (employee.isPresent()){
            repository.deleteById(id);
            return true;
        }
        else return false;
    }
}
