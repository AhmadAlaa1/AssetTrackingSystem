package com.project.AssetTrackingSystem.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.AssetTrackingSystem.model.Asset;
import com.project.AssetTrackingSystem.model.AssetAssignment;
import com.project.AssetTrackingSystem.repository.AssetAssignmentRepository;

@Service
public class AssignmentService {

    @Autowired    
    private AssetAssignmentRepository assetAssignmentRepository;

    public AssetAssignment saveAsset(AssetAssignment assetAssignmet) {
     return assetAssignmentRepository.save(assetAssignmet);
    }

    public boolean isAssetAssigned(Integer id) {
        return assetAssignmentRepository.existsByAssetIdAndReturnDateIsNull(id);
    }
    
    public AssetAssignment findByAssetId(Integer assetId) {
        return assetAssignmentRepository.findByAssetId(assetId)
            .orElseThrow(() -> new RuntimeException("No assignment found for asset ID " + assetId));
    }

}
