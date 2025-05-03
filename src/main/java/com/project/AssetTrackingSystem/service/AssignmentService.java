package com.project.AssetTrackingSystem.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.AssetTrackingSystem.model.AssetAssignment;
import com.project.AssetTrackingSystem.repository.AssetAssignmentRepository;

import java.util.List;

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
        return assetAssignmentRepository.findByAssetIdAndReturnDateIsNull(assetId)
            .orElseThrow(() -> new RuntimeException("No assignment found for asset ID " + assetId));
    }

    public List<AssetAssignment> getHistory() {
        return assetAssignmentRepository.findAll();
    }

}
