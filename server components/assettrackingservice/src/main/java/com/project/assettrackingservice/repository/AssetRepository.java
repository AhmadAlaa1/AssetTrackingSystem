package com.project.assettrackingservice.repository;

import com.project.assettrackingservice.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset,Integer> {
    
}
