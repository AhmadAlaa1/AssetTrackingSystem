package com.project.AssetTrackingSystem.dto;

public class AssetAssignmentRequest {
    private Integer staffID;
    private Integer assetID;

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public Integer getAssetID() {
        return assetID;
    }

    public void setAssetID(Integer assetID) {
        this.assetID = assetID;
    }
}
