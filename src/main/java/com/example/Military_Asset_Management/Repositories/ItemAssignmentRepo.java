package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.ItemAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ItemAssignmentRepo extends JpaRepository<ItemAssignment, Integer> {

    List<ItemAssignment> findByBaseIdAndEquipmentTypeAndAssignedDateBefore(
            Integer baseId,
            String equipmentType,
            LocalDate date          // was LocalDateTime -> the bug
    );

    List<ItemAssignment> findByBaseIdAndEquipmentTypeAndAssignedDateBetween(
            Integer baseId,
            String equipmentType,
            LocalDate start,
            LocalDate end
    );
}