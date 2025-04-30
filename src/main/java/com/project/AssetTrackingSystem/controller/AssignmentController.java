package com.project.AssetTrackingSystem.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.project.AssetTrackingSystem.dto.AssetAssignmentRequest;
import com.project.AssetTrackingSystem.dto.LeaveAssetDto;
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
    private AssetRepository assetRepository;
     
    public AssignmentController(AssetRepository assetRepository, AssignmentService assignmentService) {
        this.assetRepository = assetRepository;
        this.assignmentService = assignmentService;
    }

    @PostMapping("/assign-asset")
    public ResponseEntity<?> assignAsset(@RequestBody AssetAssignmentRequest dto) {

        Optional<Asset> optionalAsset = assetRepository.findById(dto.getAssetID());
        if (optionalAsset.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Asset with ID " + dto.getAssetID() + " does not exist.");
        }

        boolean isAlreadyAssigned = assignmentService.isAssetAssigned(dto.getAssetID());
        if (isAlreadyAssigned) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Asset is already assigned and not yet returned.");
        }

        Employee employee = new Employee();
        employee.setId(dto.getStaffID());

        AssetAssignment assetAssignment = new AssetAssignment();
        assetAssignment.setEmployee(employee);
        assetAssignment.setAsset(optionalAsset.get());
        assetAssignment.setAssignedDate(LocalDate.now());

        assignmentService.saveAsset(assetAssignment);

        return new ResponseEntity<>(assetAssignment, HttpStatus.OK);
    }

    @PutMapping("/leave-asset")
    public ResponseEntity<?> leaveAsset(@RequestBody LeaveAssetDto dto) {
        Integer assetID = dto.getAssetID();

        AssetAssignment assetAssignment;
        try {
            assetAssignment = assignmentService.findByAssetId(assetID);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No active assignment found for asset ID " + assetID);
        }

        if (assetAssignment.getReturnDate() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This asset has already been returned.");
        }

        assetAssignment.setReturnDate(LocalDate.now());
        assignmentService.saveAsset(assetAssignment);

        return new ResponseEntity<>(assetAssignment, HttpStatus.OK);
    }


}
