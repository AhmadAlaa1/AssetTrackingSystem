package com.project.assettrackingservice.repository;

import com.project.assettrackingservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    public Employee findByEmail(String email);
}
