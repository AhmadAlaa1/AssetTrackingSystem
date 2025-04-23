package com.project.AssetTrackingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.AssetTrackingSystem.model.AssetAssignment;

public interface AssetAssignmentRepository extends JpaRepository<AssetAssignment,Integer>{
    
}
