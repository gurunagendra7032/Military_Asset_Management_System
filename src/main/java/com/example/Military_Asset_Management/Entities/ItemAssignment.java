package com.example.Military_Asset_Management.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ItemAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String personName;
    private String assetName;
    private Integer assetQuantity;
    private LocalDate assignedDate;

    @ManyToOne
    private Base base;

}
