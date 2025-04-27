package com.project.AssetTrackingSystem.controller;

import com.project.AssetTrackingSystem.model.Asset;
import com.project.AssetTrackingSystem.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping("/asset")
    public ResponseEntity<?> getAllAssets() {
        return new ResponseEntity<>(assetService.getAllAssets(), HttpStatus.OK);
    }

    @PostMapping("/asset")
    public ResponseEntity<?> addAsset(@RequestBody Asset newAsset) {
        return new ResponseEntity<>(assetService.addAsset(newAsset), HttpStatus.OK);
    }

    @DeleteMapping("/asset/{id}")
    public ResponseEntity<?> deleteAsset(@PathVariable("id") Integer id) {
        assetService.deleteAsset(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/assign-asset")
    public ResponseEntity<?> assignAsset(@RequestParam("staffID") Integer staffID, @RequestParam("assetID") Integer assetID) {
        return new ResponseEntity<>(assetService.assignAsset(staffID, assetID), HttpStatus.OK);
    }

    @PutMapping("/leave-asset")
    public ResponseEntity<?> leaveAsset(@RequestParam("staffID") Integer staffID, @RequestParam("assetID") Integer assetID) {
        return new ResponseEntity<>(assetService.leaveAsset(staffID, assetID), HttpStatus.OK);
    }

    @PutMapping("/maintain-asset")
    public ResponseEntity<?> maintainAsset(@RequestParam("managerID") Integer managerID, @RequestParam("assetID") Integer assetID) {
        return new ResponseEntity<>(assetService.maintainAsset(managerID, assetID), HttpStatus.OK);
    }
}
