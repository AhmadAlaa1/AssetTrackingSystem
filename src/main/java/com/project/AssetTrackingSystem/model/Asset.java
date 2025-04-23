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
        MAINTAINCE
    }
    
}
