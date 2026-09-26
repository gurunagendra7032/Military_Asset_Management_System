package com.example.Military_Asset_Management.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;


@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   private String equipmentType;
   private String equipmentName;
   @NotNull
   private Integer quantity;
   private LocalDate purchaseDate;
   @ManyToOne
   private Base base;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}
