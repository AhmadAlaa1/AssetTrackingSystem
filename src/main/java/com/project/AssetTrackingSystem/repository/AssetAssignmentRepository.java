package com.project.AssetTrackingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.AssetTrackingSystem.model.AssetAssignment;

import java.util.Optional;

public interface AssetAssignmentRepository extends JpaRepository<AssetAssignment,Integer>{
    Optional<AssetAssignment> findByAssetIdAndReturnDateIsNull(Integer assetId);
}
