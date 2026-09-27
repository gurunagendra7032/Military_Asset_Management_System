package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenditureRepo extends JpaRepository<Expenditure,Integer> {

    List<Expenditure> findByBaseIdAndEquipmentTypeAndDateBefore(
            Integer baseId,
            String equipmentType,
            LocalDateTime date
    );

    List<Expenditure> findByBaseIdAndEquipmentTypeAndDateBetween(
            Integer baseId,
            String equipmentType,
            LocalDateTime start,
            LocalDateTime end
    );
}
