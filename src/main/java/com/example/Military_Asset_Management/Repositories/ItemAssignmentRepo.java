package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.ItemAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ItemAssignmentRepo extends JpaRepository<ItemAssignment,Integer> {

    List<ItemAssignment> findByBaseIdAndEquipmentTypeAndAssignedDateBefore(
            Integer baseId,
            String equipmentType,
            LocalDateTime date
    );

    List<ItemAssignment> findByBaseIdAndEquipmentTypeAndAssignedDateBetween(
            Integer baseId,
            String equipmentType,
            LocalDateTime start,
            LocalDateTime end
    );
}