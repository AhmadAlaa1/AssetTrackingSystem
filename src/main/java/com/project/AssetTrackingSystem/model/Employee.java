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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}

