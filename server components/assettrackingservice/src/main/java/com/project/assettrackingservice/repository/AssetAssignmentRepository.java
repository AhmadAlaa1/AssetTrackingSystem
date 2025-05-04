package com.project.assettrackingservice.repository;

import com.project.assettrackingservice.model.AssetAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetAssignmentRepository extends JpaRepository<AssetAssignment,Integer>{
    Optional<AssetAssignment> findByAssetIdAndReturnDateIsNull(Integer assetId);
}
