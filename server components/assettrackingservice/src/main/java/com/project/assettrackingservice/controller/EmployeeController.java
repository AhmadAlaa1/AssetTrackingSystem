package com.project.assettrackingservice.controller;

import com.project.assettrackingservice.model.Employee;
import com.project.assettrackingservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracker")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/staff")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/staff")
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        return employeeService.saveEmployee(newEmployee);
    }

    @DeleteMapping("/staff/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/emp-by-email/{email}")
    public Employee getEmployeeByEmail(@PathVariable String email) { return employeeService.getByEmail(email); }
}
