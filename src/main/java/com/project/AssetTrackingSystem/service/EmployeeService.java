package com.project.AssetTrackingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.AssetTrackingSystem.model.Employee;
import com.project.AssetTrackingSystem.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository empRepo;

    public Employee getByEmail(String email){
        return empRepo.findByEmail(email);
    }
    
    public Employee saveEmployee(Employee employee){
        return empRepo.save(employee);
    }
}
