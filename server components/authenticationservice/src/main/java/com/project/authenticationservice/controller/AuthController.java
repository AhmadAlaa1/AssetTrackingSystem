package com.project.authenticationservice.controller;

import com.project.authenticationservice.dto.LoginRequest;
import com.project.authenticationservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RestTemplate restTemplate;

    // NOTE:
    // ------------------------------------------------
    // IT IS NOT IDEAL TO HAVE TO DUPLICATE
    // THE MODEL DEFINITION BETWEEN SERVICE DIRECTORIES,
    // BUT FOR THE SAKE OF THIS PROJECT IT SHOULD SUFFICE.
    // ....
    // PREFERABLY YOU SHOULD HAVE A COMMON DECOUPLED
    // LIBRARY THAT CONTAINS ALL THE DTO DEFINITIONS AND
    // EXCLUSIVELY USE THAT TO INTERCONNECT THE SERVICES.
    // -------------------------------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        Employee employee = restTemplate.getForObject("http://assettrackingservice/api/tracker/emp-by-email/" + loginRequest.getEmail(), Employee.class);
        if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            throw new Exception("Incorrect email or password");
        }
    }
}