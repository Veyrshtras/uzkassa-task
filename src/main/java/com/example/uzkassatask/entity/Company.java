package com.example.uzkassatask.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
public class Company extends BaseEntity{

    private String name;
    private String address;
    private long zipCode;

    @Enumerated(EnumType.STRING)
    private State state=State.ACTIVE;

}
