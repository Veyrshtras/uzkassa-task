package com.example.uzkassatask.service.serviceImpl;

import com.example.uzkassatask.dto.CompDto;
import com.example.uzkassatask.entity.Company;
import com.example.uzkassatask.entity.State;
import com.example.uzkassatask.repository.CompanyRepository;
import com.example.uzkassatask.service.CompanyService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Company editCompany(CompDto compDto, long id) throws NotFound {
        Optional<Company> company=repository.findById(id);
        if (company.isPresent()){
            company.get().setAddress(compDto.getAddress());
            company.get().setName(compDto.getName());
            company.get().setZipCode(compDto.getZipCode());
            return company.get();
        }
        else throw new NotFound();
    }

    @Override
    public Company blockCompany(long id) throws NotFound {
        Optional<Company> company=repository.findById(id);
        if (company.isPresent()){

            company.get().setState(State.BLOCKED);
            return company.get();
        }
        else throw new NotFound();
    }
}
