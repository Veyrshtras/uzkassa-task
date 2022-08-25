package com.example.uzkassatask.controller;

import com.example.uzkassatask.model.SignInModel;
import com.example.uzkassatask.model.SignUpModel;
import com.example.uzkassatask.service.OtherServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/other")
public class OtherController {

    private final OtherServices services;

    public OtherController(OtherServices services) {
        this.services = services;
    }

    @GetMapping("{code}")
    public ResponseEntity getCurrencyRate(@PathVariable String code){
        if (services.getCurrencyRateApiByCode(code)!=null)
            return ResponseEntity.ok(services.getCurrencyRateApiByCode(code));
        else
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
    }

    @PostMapping("signup")
    public ResponseEntity signUp(@RequestBody SignUpModel signUpModel) throws Exception {
        return ResponseEntity.ok(services.signUp(signUpModel));
    }


    @PostMapping("signin")
    public ResponseEntity signIn(@RequestBody SignInModel signInModel){
        return ResponseEntity.ok(services.singIn(signInModel));
    }

}
