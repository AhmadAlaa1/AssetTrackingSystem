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

    public Asset findById(Integer id){
        return assetRepository.findById(id).orElse(null);
    }

    public Asset maintainAsset(Integer assetID) {
        Asset asset = findById(assetID);
        asset.setStatus(Asset.Status.MAINTENANCE);
        return assetRepository.save(asset);
    }

    public Asset unmaintainAsset(Integer assetID) {
        Asset asset = findById(assetID);
        asset.setStatus(Asset.Status.AVAILABLE);
        return assetRepository.save(asset);
    }
}
