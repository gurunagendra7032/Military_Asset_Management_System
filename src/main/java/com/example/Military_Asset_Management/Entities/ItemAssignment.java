package com.example.Military_Asset_Management.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class ItemAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    private String personName;
    private String equipmentType;
    private String assetName;
    @NotNull
    private Integer assetQuantity;
    private LocalDate assignedDate;

    @ManyToOne
    private Base base;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotEmpty String getPersonName() {
        return personName;
    }

    public void setPersonName(@NotEmpty String personName) {
        this.personName = personName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public @NotNull Integer getAssetQuantity() {
        return assetQuantity;
    }

    public void setAssetQuantity(@NotNull Integer assetQuantity) {
        this.assetQuantity = assetQuantity;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }
}
