package com.project.AssetTrackingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import com.project.AssetTrackingSystem.service.EmployeeService;
import com.project.AssetTrackingSystem.dto.LoginRequest;
import com.project.AssetTrackingSystem.dto.RegisterRequest;
import com.project.AssetTrackingSystem.model.Employee;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthController {
    
    @Autowired
    private EmployeeService empService;
    private Employee employee = new Employee(); 

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            employee = empService.getByEmail(loginRequest.getEmail());
            if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
                return new ResponseEntity<>(employee, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (java.lang.Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            employee.setName(registerRequest.getName());
            employee.setEmail(registerRequest.getEmail());
            employee.setPassword(registerRequest.getPassword());
            employee.setRole(Employee.Role.STAFF);
            empService.saveEmployee(employee);

            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (java.lang.Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}