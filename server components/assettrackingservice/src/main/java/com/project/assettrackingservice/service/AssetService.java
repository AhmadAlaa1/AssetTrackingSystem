package com.project.assettrackingservice.service;

import com.project.assettrackingservice.model.Asset;
import com.project.assettrackingservice.repository.AssetRepository;
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

    private Asset putInMaintenance(Integer assetID) {
        Asset asset = findById(assetID);
        asset.setStatus(Asset.Status.MAINTENANCE);
        return assetRepository.save(asset);
    }

    private Asset putOutOfMaintenance(Integer assetID) {
        Asset asset = findById(assetID);
        asset.setStatus(Asset.Status.AVAILABLE);
        return assetRepository.save(asset);
    }

    public Asset maintainAsset(Integer assetID) throws Exception {
        Asset asset = findById(assetID);

        if (asset == null) throw new Exception("Asset not found");

        switch (asset.getStatus()) {
            case MAINTENANCE:
                return putOutOfMaintenance(assetID);
            case AVAILABLE:
                return putInMaintenance(assetID);
            case INUSE:
                throw new Exception("Asset is in use");
            default:
                return null;
        }
    }
}
