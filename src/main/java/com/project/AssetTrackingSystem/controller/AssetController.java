package com.project.AssetTrackingSystem.controller;

import com.project.AssetTrackingSystem.model.Asset;
import com.project.AssetTrackingSystem.service.AssetService;
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
    public ResponseEntity<?> getAllAssets() throws Exception {
        return new ResponseEntity<>(assetService.getAllAssets(), HttpStatus.OK);
    }

    @PostMapping("/asset")
    public ResponseEntity<?> addAsset(@RequestBody Asset newAsset) throws Exception {
        return new ResponseEntity<>(assetService.addAsset(newAsset), HttpStatus.OK);
    }

    @DeleteMapping("/asset/{id}")
    public ResponseEntity<?> deleteAsset(@PathVariable("id") Integer id) throws Exception {
        assetService.deleteAsset(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

   @PutMapping("/maintain-asset")
    public ResponseEntity<?> maintainAsset(@RequestParam("assetID") Integer assetID) throws Exception {
        return new ResponseEntity<>(assetService.maintainAsset(assetID), HttpStatus.OK);
    }
}
