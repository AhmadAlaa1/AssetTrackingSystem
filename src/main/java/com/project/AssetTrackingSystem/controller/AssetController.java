package com.project.AssetTrackingSystem.controller;

import com.project.AssetTrackingSystem.dto.MaintainAssetDto;
import com.project.AssetTrackingSystem.model.Asset;
import com.project.AssetTrackingSystem.service.AssetService;
import com.project.AssetTrackingSystem.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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

   @PutMapping("/asset-maintain")
    public ResponseEntity<?> maintainAsset(@RequestParam("assetID") Integer assetID) {
        Integer id = assetID;

        Asset asset;
        try {
            asset = assetService.findById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Asset with ID " + id + " not found.");
        }

        if (asset.getStatus() == Asset.Status.MAINTENANCE) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("⚠️ Asset is already under maintenance.");
        }

        assetService.maintainAsset(id);

        return new ResponseEntity<>(assetService.findById(id), HttpStatus.OK);
    }

}
