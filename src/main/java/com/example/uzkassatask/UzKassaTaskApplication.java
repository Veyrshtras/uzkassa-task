package com.example.uzkassatask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UzKassaTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(UzKassaTaskApplication.class, args);
    }

}
