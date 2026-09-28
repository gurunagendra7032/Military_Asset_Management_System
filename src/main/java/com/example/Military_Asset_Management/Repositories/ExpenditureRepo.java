package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExpenditureRepo extends JpaRepository<Expenditure,Integer> {

    List<Expenditure> findByBaseIdAndEquipmentTypeAndDateBefore(
            Integer baseId,
            String equipmentType,
            LocalDate date
    );

    List<Expenditure> findByBaseIdAndEquipmentTypeAndDateBetween(
            Integer baseId,
            String equipmentType,
            LocalDate start,
            LocalDate end
    );
}
