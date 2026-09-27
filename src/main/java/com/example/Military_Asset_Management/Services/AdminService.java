package com.example.Military_Asset_Management.Services;

import com.example.Military_Asset_Management.Entities.*;
import com.example.Military_Asset_Management.Repositories.ExpenditureRepo;
import com.example.Military_Asset_Management.Repositories.ItemAssignmentRepo;
import com.example.Military_Asset_Management.Repositories.PurchaseRepo;
import com.example.Military_Asset_Management.Repositories.TransferRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AdminService {
    @Autowired
    private PurchaseRepo purchaseRepo;
    @Autowired
    private TransferRepo transferRepo;
    @Autowired
    private ItemAssignmentRepo itemAssignmentRepo;
    @Autowired
    private ExpenditureRepo expenditureRepo;

    public int getOpeningBalance(Integer baseId, String equipmentType, LocalDate date){

        LocalDateTime dateTime = date.atStartOfDay();

        List<Purchase> purchase=purchaseRepo
                .findByBaseIdAndEquipmentTypeAndPurchaseDateBefore(baseId,equipmentType,date);

        List<Transfer> transferIn=transferRepo
                .findByToBaseIdAndEquipmentTypeAndTransferDateBefore(baseId,equipmentType,dateTime);

        List<Transfer> transferOut=transferRepo
                .findByFromBaseIdAndEquipmentTypeAndTransferDateBefore(baseId,equipmentType,dateTime);

        List<ItemAssignment> itemAssignments=itemAssignmentRepo
                .findByBaseIdAndEquipmentTypeAndAssignedDateBefore(baseId,equipmentType,dateTime);

        List<Expenditure> expenditure = expenditureRepo
                .findByBaseIdAndEquipmentTypeAndDateBefore(baseId,equipmentType,dateTime);

        int purchaseQuantity = purchase.stream()
                .mapToInt(Purchase::getQuantity)
                .sum();

        int transferInQuantity = transferIn.stream()
                .mapToInt(Transfer::getEquipmentQuantity)
                .sum();

        int transferOutQuantity = transferOut.stream()
                .mapToInt(Transfer::getEquipmentQuantity)
                .sum();

        int itemAssignQuantity= itemAssignments.stream()
                .mapToInt(ItemAssignment :: getAssetQuantity)
                .sum();

        int expenditureQuantity= expenditure.stream()
                .mapToInt(Expenditure :: getEquipmentQuantity)
                .sum();

        int OpenBalance = purchaseQuantity+transferInQuantity-transferOutQuantity-itemAssignQuantity-expenditureQuantity;


        return OpenBalance;

    }


    public int closingBalance(Integer baseId, String equipmentType, LocalDate date){

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        List<Purchase> purchase=purchaseRepo
                .findByBaseIdAndEquipmentTypeAndPurchaseDate(baseId,equipmentType,date);

        List<Transfer> transferIn=transferRepo
                .findByToBaseIdAndEquipmentTypeAndTransferDateBetween(baseId,equipmentType,start,end);

        List<Transfer> transferOut=transferRepo
                .findByFromBaseIdAndEquipmentTypeAndTransferDateBetween(baseId,equipmentType,start,end);

        List<ItemAssignment> itemAssignments=itemAssignmentRepo
                .findByBaseIdAndEquipmentTypeAndAssignedDateBetween(baseId,equipmentType,start,end);

        List<Expenditure> expenditure = expenditureRepo
                .findByBaseIdAndEquipmentTypeAndDateBetween(baseId,equipmentType,start,end);

        int purchaseQuantity = purchase.stream()
                .mapToInt(Purchase::getQuantity)
                .sum();

        int transferInQuantity = transferIn.stream()
                .mapToInt(Transfer::getEquipmentQuantity)
                .sum();

        int transferOutQuantity = transferOut.stream()
                .mapToInt(Transfer::getEquipmentQuantity)
                .sum();

        int itemAssignQuantity= itemAssignments.stream()
                .mapToInt(ItemAssignment :: getAssetQuantity)
                .sum();

        int expenditureQuantity= expenditure.stream()
                .mapToInt(Expenditure :: getEquipmentQuantity)
                .sum();

        int closingBalance = purchaseQuantity+transferInQuantity-transferOutQuantity-itemAssignQuantity-expenditureQuantity;




        return getOpeningBalance(baseId,equipmentType,date)+closingBalance;


    }


    public int NetMovement(Integer baseId, String equipmentType, LocalDate date){

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        List<Purchase> purchase=purchaseRepo
                .findByBaseIdAndEquipmentTypeAndPurchaseDate(baseId,equipmentType,date);

        List<Transfer> transferIn=transferRepo
                .findByToBaseIdAndEquipmentTypeAndTransferDateBetween(baseId,equipmentType,start,end);

        List<Transfer> transferOut=transferRepo
                .findByFromBaseIdAndEquipmentTypeAndTransferDateBetween(baseId,equipmentType,start,end);

        int purchaseQuantity = purchase.stream()
                .mapToInt(Purchase::getQuantity)
                .sum();

        int transferInQuantity = transferIn.stream()
                .mapToInt(Transfer::getEquipmentQuantity)
                .sum();

        int transferOutQuantity = transferOut.stream()
                .mapToInt(Transfer::getEquipmentQuantity)
                .sum();

        int netmovemet=purchaseQuantity+transferInQuantity-transferOutQuantity;

        return netmovemet;
    }
}
