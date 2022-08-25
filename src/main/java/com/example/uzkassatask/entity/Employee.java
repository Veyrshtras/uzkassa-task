package com.example.uzkassatask.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Employee extends BaseEntity {

    private String name;
    private String username;
    private String password;

    @Column(unique = true)
    private String email;
    private boolean status=false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Company company;

    @Enumerated(EnumType.STRING)
    public UserRole role=UserRole.USER;
}
