package com.project.assettrackingservice.controller;

import com.project.assettrackingservice.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracker")
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
