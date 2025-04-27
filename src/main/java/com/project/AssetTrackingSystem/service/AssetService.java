package com.project.AssetTrackingSystem.service;
import com.project.AssetTrackingSystem.model.Asset;
import com.project.AssetTrackingSystem.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Asset addAsset(Asset newAsset) {
        return assetRepository.save(newAsset);
    }

    public void deleteAsset(Integer id) {
        assetRepository.deleteById(id);
    }

    /* ======================= */
    /* UNIMPLEMENTED FUNCTIONS */
    /* ======================= */
    public Asset assignAsset(Integer staffID, Integer assetID) {
        return new Asset();
    }

    public Asset leaveAsset(Integer staffID, Integer assetID) {
        return new Asset();
    }

    public Asset maintainAsset(Integer staffID, Integer assetID) {
        return new Asset();
    }
}
