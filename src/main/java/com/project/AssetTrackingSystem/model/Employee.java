package com.project.AssetTrackingSystem.model;

import jakarta.persistence.*;


@Table(name="Employee")
@Entity

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;
    
    @Column(name="email",unique = true)
    private String email;
  
    @Column(name="password")
    private String password;
    
    public enum Role {
        MANAGER,
        STAFF
    }
    
}

