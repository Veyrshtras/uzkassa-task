package com.example.uzkassatask.controller;

import com.example.uzkassatask.dto.CompDto;
import com.example.uzkassatask.service.CompanyService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/company")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PutMapping("{id}")
    public ResponseEntity edit(@RequestBody CompDto dto, @PathVariable Long id) throws NotFound {
        return ResponseEntity.ok(service.editCompany(dto, id));
    }

    @PutMapping("blockCompany/{id}")
    public ResponseEntity block(@PathVariable Long id) throws NotFound {
        return ResponseEntity.ok(service.blockCompany(id));
    }
}
