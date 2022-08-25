package com.example.uzkassatask.controller;

import com.example.uzkassatask.dto.EmpDto;
import com.example.uzkassatask.service.EmployeeService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee/{companyId}")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable Long id, @PathVariable Long companyId){
        return ResponseEntity.ok(service.get(id, companyId));
    }

    @GetMapping
    public ResponseEntity getAll(@PathVariable Long companyId){
        return ResponseEntity.ok(service.getAll(companyId));
    }

    @PutMapping("activate/{id}")
    public ResponseEntity activate(@PathVariable long companyId, @PathVariable long id){
        return ResponseEntity.ok(service.activate(id));
    }



    @PutMapping("{id}")
    public ResponseEntity edit(@RequestBody EmpDto dto, @PathVariable Long id,@PathVariable Long companyId) throws NotFound {
        return ResponseEntity.ok(service.edit(dto, id, companyId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id,@PathVariable Long companyId) throws NotFound {
        return ResponseEntity.ok(service.delete(id, companyId));
    }

}
