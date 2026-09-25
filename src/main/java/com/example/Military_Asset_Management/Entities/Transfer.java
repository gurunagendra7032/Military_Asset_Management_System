package com.example.Military_Asset_Management.Entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String equipmentType;
    private Integer equipmentQuantity;
    private LocalDateTime transferDate;

    @ManyToOne
    private Base fromBase;

    @ManyToOne
    private Base toBase;

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

    public Integer getEquipmentQuantity() {
        return equipmentQuantity;
    }

    public void setEquipmentQuantity(Integer equipmentQuantity) {
        this.equipmentQuantity = equipmentQuantity;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public Base getFromBase() {
        return fromBase;
    }

    public void setFromBase(Base fromBase) {
        this.fromBase = fromBase;
    }

    public Base getToBase() {
        return toBase;
    }

    public void setToBase(Base toBase) {
        this.toBase = toBase;
    }
}
