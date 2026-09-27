package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface PurchaseRepo extends JpaRepository<Purchase,Integer> {

    List<Purchase> findByBaseIdAndEquipmentTypeAndPurchaseDateBefore(
            Integer baseId,
            String equipmentType,
            LocalDate date
    );

    List<Purchase> findByBaseIdAndEquipmentTypeAndPurchaseDate(
            Integer baseId,
            String equipmentType,
            LocalDate date
    );
}
