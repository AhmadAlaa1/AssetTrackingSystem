package com.project.assettrackingservice.service;

import com.project.assettrackingservice.model.Employee;
import com.project.assettrackingservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository empRepo;

    public List<Employee> getAllEmployees() { return empRepo.findAll(); }

    public Employee getByEmail(String email){
        return empRepo.findByEmail(email);
    }
    
    public Employee saveEmployee(Employee employee){
        return empRepo.save(employee);
    }

    public void deleteEmployee(Integer id) { empRepo.deleteById(id); }
}
