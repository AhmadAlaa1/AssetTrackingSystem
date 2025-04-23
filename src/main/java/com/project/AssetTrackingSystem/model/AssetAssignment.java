package com.project.AssetTrackingSystem.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Table(name="AssetAssignment")
@Entity

public class AssetAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "assignedDate")
    private LocalDate assignedDate;
    
    @Column(name = "returnDate")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    

}
