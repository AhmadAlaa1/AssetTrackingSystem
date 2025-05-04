package com.project.assettrackingservice.service;

import com.project.assettrackingservice.model.Asset;
import com.project.assettrackingservice.model.AssetAssignment;
import com.project.assettrackingservice.model.Employee;
import com.project.assettrackingservice.repository.AssetAssignmentRepository;
import com.project.assettrackingservice.repository.AssetRepository;
import com.project.assettrackingservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired    
    private AssetAssignmentRepository assetAssignmentRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<AssetAssignment> getHistory() {
        return assetAssignmentRepository.findAll();
    }

    public AssetAssignment assignAsset(Integer assetID, Integer staffID) throws Exception {
        Optional<Asset> optionalAsset = assetRepository.findById(assetID);
        Optional<Employee> optionalEmployee = employeeRepository.findById(staffID);

        if (optionalAsset.isEmpty()) {
            throw new Exception("Asset with ID " + assetID + " does not exist.");
        }

        if (optionalEmployee.isEmpty()) {
            throw new Exception("Employee with ID " + staffID + " does not exist.");
        }

        Employee staff =  optionalEmployee.get();
        Asset asset = optionalAsset.get();

        boolean isAlreadyAssigned = (asset.getUsedBy() != null);
        if (isAlreadyAssigned) {
            throw new Exception("Asset is already assigned and not yet returned.");
        }

        asset.setUsedBy(staffID);
        asset.setStatus(Asset.Status.INUSE);

        AssetAssignment assetAssignment = new AssetAssignment();
        assetAssignment.setEmployee(staff);
        assetAssignment.setAsset(asset);
        assetAssignment.setAssignedDate(LocalDate.now());

        return assetAssignmentRepository.save(assetAssignment);
    }

    public Asset leaveAsset(Integer assetID) throws Exception {
        Optional<Asset> optionalAsset = assetRepository.findById(assetID);
        Optional<AssetAssignment> optionalAssetAssignment = assetAssignmentRepository.findByAssetIdAndReturnDateIsNull(assetID);

        if (optionalAsset.isEmpty()) {
            throw new Exception("asset " + assetID + " not found.");
        }

        if (optionalAssetAssignment.isEmpty()) {
            throw new Exception("assignment for " + assetID + " not found.");
        }

        Asset asset = optionalAsset.get();
        AssetAssignment assignment = optionalAssetAssignment.get();

        asset.setUsedBy(null);
        asset.setStatus(Asset.Status.AVAILABLE);

        assignment.setReturnDate(LocalDate.now());
        assetAssignmentRepository.save(assignment);

        return asset;
    }
}
