package com.example.Military_Asset_Management.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String equipmentName;
    private String equipmentType;
    @NotNull
    private Integer equipmentQuantity;
    private String reason;
    @ManyToOne
    private Base base;
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getEquipmentQuantity() {
        return equipmentQuantity;
    }

    public void setEquipmentQuantity(Integer equipmentQuantity) {
        this.equipmentQuantity = equipmentQuantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }
}
