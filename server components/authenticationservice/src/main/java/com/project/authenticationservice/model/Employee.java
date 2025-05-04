package com.project.authenticationservice.model;

public class Employee {
    private Integer id;

    private String name;

    private Role role = Role.STAFF;

    private String email;

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

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    
}

