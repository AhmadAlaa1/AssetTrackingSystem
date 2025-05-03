package com.project.AssetTrackingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.project.AssetTrackingSystem.service.AssignmentService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AssignmentController {
    
    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/history")
    public ResponseEntity<?> getHistory() throws Exception {
        return new ResponseEntity<>(assignmentService.getHistory(), HttpStatus.OK);
    }

    @PutMapping("/assign-asset")
    public ResponseEntity<?> assignAsset(@RequestParam("staffID") Integer staffID, @RequestParam("assetID") Integer assetID) throws Exception {
        return new ResponseEntity<>(assignmentService.assignAsset(assetID, staffID), HttpStatus.OK);
    }

    @PutMapping("/leave-asset")
    public ResponseEntity<?> leaveAsset(@RequestParam("assetID") Integer assetID) throws Exception {
        return new ResponseEntity<>(assignmentService.leaveAsset(assetID), HttpStatus.OK);
    }
}
