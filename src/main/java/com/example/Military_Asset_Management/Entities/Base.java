package com.example.Military_Asset_Management.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank
    private String baseName;
    @NotBlank
    private String baseLocation;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }


}
