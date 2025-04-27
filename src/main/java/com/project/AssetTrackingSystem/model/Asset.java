package com.project.AssetTrackingSystem.model;
import jakarta.persistence.*;

@Table(name="Asset")
@Entity

public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="serialNumber",unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    public enum Status {
        AVAILABLE,
        INUSE,
        MAINTENANCE
    }

     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
