package com.project.AssetTrackingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.AssetTrackingSystem.model.Asset;

public interface AssetRepository extends JpaRepository<Asset,Integer> {
    
}
