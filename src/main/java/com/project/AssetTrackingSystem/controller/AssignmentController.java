package com.project.AssetTrackingSystem.controller;

import java.time.LocalDate;

import com.project.AssetTrackingSystem.repository.EmployeeRepository;
import com.project.AssetTrackingSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.project.AssetTrackingSystem.model.Asset;
import com.project.AssetTrackingSystem.model.AssetAssignment;
import com.project.AssetTrackingSystem.model.Employee;
import com.project.AssetTrackingSystem.repository.AssetRepository;
import com.project.AssetTrackingSystem.service.AssignmentService;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AssignmentController {
    
    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/history")
    public ResponseEntity<?> getHistory() {
        return new ResponseEntity<>(assignmentService.getHistory(), HttpStatus.OK);
    }

    @PutMapping("/assign-asset")
    public ResponseEntity<?> assignAsset(@RequestParam("staffID") Integer staffID, @RequestParam("assetID") Integer assetID) {

        Optional<Asset> optionalAsset = assetRepository.findById(assetID);
        Optional<Employee> optionalEmployee = employeeRepository.findById(staffID);

        if (optionalAsset.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Asset with ID " + assetID + " does not exist.");
        }

        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee with ID " + staffID + " does not exist.");
        }

        boolean isAlreadyAssigned = assignmentService.isAssetAssigned(assetID);
        if (isAlreadyAssigned) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Asset is already assigned and not yet returned.");
        }

        Employee staff =  optionalEmployee.get();
        Asset asset = optionalAsset.get();

        asset.setUsedBy(staffID);
        asset.setStatus(Asset.Status.INUSE);

        AssetAssignment assetAssignment = new AssetAssignment();
        assetAssignment.setEmployee(staff);
        assetAssignment.setAsset(asset);
        assetAssignment.setAssignedDate(LocalDate.now());

        assignmentService.saveAsset(assetAssignment);

        return new ResponseEntity<>(assetAssignment, HttpStatus.OK);
    }

    @PutMapping("/leave-asset")
    public ResponseEntity<?> leaveAsset(@RequestParam("assetID") Integer assetID) {
        Optional<Asset> optionalAsset = assetRepository.findById(assetID);
        AssetAssignment assetAssignment;

        try {
            assetAssignment = assignmentService.findByAssetId(assetID);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No active assignment found for asset ID " + assetID);
        }

        if (optionalAsset.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("asset " + assetID + " not found.");
        }

        Asset asset = optionalAsset.get();

        asset.setUsedBy(null);
        asset.setStatus(Asset.Status.AVAILABLE);

        assetAssignment.setReturnDate(LocalDate.now());
        assignmentService.saveAsset(assetAssignment);

        return new ResponseEntity<>(asset, HttpStatus.OK);
    }
}
