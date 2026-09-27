package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransferRepo extends JpaRepository<Transfer,Integer> {

    List <Transfer> findByToBaseIdAndEquipmentTypeAndTransferDateBefore(
            Integer baseId,
            String equipmentType,
            LocalDateTime date
    );

    List<Transfer> findByFromBaseIdAndEquipmentTypeAndTransferDateBefore(
            Integer baseId,
            String equipmentType,
            LocalDateTime date
    );


    List<Transfer> findByToBaseIdAndEquipmentTypeAndTransferDateBetween(
            Integer baseId,
            String equipmentType,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Transfer> findByFromBaseIdAndEquipmentTypeAndTransferDateBetween(
            Integer baseId,
            String equipmentType,
            LocalDateTime start,
            LocalDateTime end
    );


}
