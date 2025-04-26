package com.project.AssetTrackingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.project.AssetTrackingSystem.service.EmployeeService;
import com.project.AssetTrackingSystem.dto.LoginRequest;
import com.project.AssetTrackingSystem.dto.RegisterRequest;
import com.project.AssetTrackingSystem.model.Employee;

@RestController
@RequestMapping("/api")
public class AuthController {
    
    @Autowired
    private EmployeeService empService;
    private Employee employee = new Employee(); 

    @PostMapping("login")
    public Employee login(@RequestBody LoginRequest loginRequest){
        employee=empService.getByEmail(loginRequest.getEmail());
        if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
            return employee;
        } else {
            return null;
        }

    }
    
    @PostMapping("regsiter")
    public Employee register(@RequestBody RegisterRequest registerRequest){
        employee.setName(registerRequest.getName());
        employee.setEmail(registerRequest.getEmail());
        employee.setPassword(registerRequest.getPassword());
        employee.setRole(Employee.Role.STAFF);
        empService.saveEmployee(employee);

        return employee;

    }
}