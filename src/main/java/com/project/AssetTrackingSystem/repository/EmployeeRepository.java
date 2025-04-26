package com.project.AssetTrackingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.AssetTrackingSystem.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
    public Employee findByEmail(String email);
}
