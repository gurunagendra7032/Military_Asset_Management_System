package com.example.Military_Asset_Management.Services;

import com.example.Military_Asset_Management.DTOs.SignupReqDto;
import com.example.Military_Asset_Management.DTOs.SignupResDto;
import com.example.Military_Asset_Management.Entities.*;
import com.example.Military_Asset_Management.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService {

    @Autowired private PurchaseRepo purchaseRepo;
    @Autowired private TransferRepo transferRepo;
    @Autowired private ItemAssignmentRepo itemAssignmentRepo;
    @Autowired private ExpenditureRepo expenditureRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private BaseRepo baseRepo;
    @Autowired private UserRepo userRepo;

    // ---------- helpers ----------
    private int sumPurchases(List<Purchase> list) {
        return list.stream().mapToInt(Purchase::getQuantity).sum();
    }

    private int sumTransfers(List<Transfer> list) {
        return list.stream().mapToInt(Transfer::getEquipmentQuantity).sum();
    }

    private int sumAssignments(List<ItemAssignment> list) {
        return list.stream().mapToInt(ItemAssignment::getAssetQuantity).sum();
    }

    private int sumExpenditures(List<Expenditure> list) {
        return list.stream().mapToInt(Expenditure::getEquipmentQuantity).sum();
    }


    public int getOpeningBalance(Integer baseId, String equipmentType, LocalDate date) {

        LocalDateTime dateTime = date.atStartOfDay();

        int purchases = sumPurchases(purchaseRepo
                .findByBaseIdAndEquipmentTypeAndPurchaseDateBefore(baseId, equipmentType, date));

        int transferIn = sumTransfers(transferRepo
                .findByToBaseIdAndEquipmentTypeAndTransferDateBefore(baseId, equipmentType, dateTime));

        int transferOut = sumTransfers(transferRepo
                .findByFromBaseIdAndEquipmentTypeAndTransferDateBefore(baseId, equipmentType, dateTime));

        int assigned = sumAssignments(itemAssignmentRepo
                .findByBaseIdAndEquipmentTypeAndAssignedDateBefore(baseId, equipmentType, date));

        int expended = sumExpenditures(expenditureRepo
                .findByBaseIdAndEquipmentTypeAndDateBefore(baseId, equipmentType, date));

        return purchases + transferIn - transferOut - assigned - expended;
    }


    public int closingBalance(Integer baseId, String equipmentType, LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);

        int purchases = sumPurchases(purchaseRepo
                .findByBaseIdAndEquipmentTypeAndPurchaseDate(baseId, equipmentType, date));

        int transferIn = sumTransfers(transferRepo
                .findByToBaseIdAndEquipmentTypeAndTransferDateBetween(baseId, equipmentType, start, end));

        int transferOut = sumTransfers(transferRepo
                .findByFromBaseIdAndEquipmentTypeAndTransferDateBetween(baseId, equipmentType, start, end));

        int assigned = sumAssignments(itemAssignmentRepo
                .findByBaseIdAndEquipmentTypeAndAssignedDateBetween(baseId, equipmentType, date, date));

        int expended = sumExpenditures(expenditureRepo
                .findByBaseIdAndEquipmentTypeAndDateBetween(baseId, equipmentType, date, date));

        int dayMovement = purchases + transferIn - transferOut - assigned - expended;

        return getOpeningBalance(baseId, equipmentType, date) + dayMovement;
    }


    public int NetMovement(Integer baseId, String equipmentType, LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);   // FIX

        int purchases = sumPurchases(purchaseRepo
                .findByBaseIdAndEquipmentTypeAndPurchaseDate(baseId, equipmentType, date));

        int transferIn = sumTransfers(transferRepo
                .findByToBaseIdAndEquipmentTypeAndTransferDateBetween(baseId, equipmentType, start, end));

        int transferOut = sumTransfers(transferRepo
                .findByFromBaseIdAndEquipmentTypeAndTransferDateBetween(baseId, equipmentType, start, end));

        return purchases + transferIn - transferOut;
    }


    public int getItemAssign(Integer baseId, String equipmentType, LocalDate date){
        int assigned = sumAssignments(itemAssignmentRepo
                .findByBaseIdAndEquipmentTypeAndAssignedDateBetween(baseId, equipmentType, date, date));
        return assigned;
    }

    public int getExpendItem(Integer baseId, String equipmentType, LocalDate date){
        int expend = sumExpenditures(expenditureRepo
                .findByBaseIdAndEquipmentTypeAndDateBetween(baseId, equipmentType, date, date));
        return expend;
    }





    public SignupResDto saveBaseCommander(SignupReqDto signupReqDto) {

        User user = new User();
        user.setUserName(signupReqDto.getName());
        user.setUserEmail(signupReqDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupReqDto.getPassword()));

        Base base = baseRepo.findById(signupReqDto.getBaseId())
                .orElseThrow(() -> new RuntimeException("Base not found"));
        user.setBase(base);
        user.setRole(Role.BASE_COMMANDER);
        userRepo.save(user);

        SignupResDto res = new SignupResDto();
        res.setName(user.getUserName());
        res.setEmail(user.getUserEmail());
        return res;
    }

    public SignupResDto saveAdmin(SignupReqDto signupReqDto){
        User user=new User();
        user.setUserName(signupReqDto.getName());
        user.setUserEmail(signupReqDto.getEmail());
        user.setUserName(signupReqDto.getPassword());
        user.setRole(Role.ADMIN);
        userRepo.save(user);

        SignupResDto res = new SignupResDto();
        res.setName(user.getUserName());
        res.setEmail(user.getUserEmail());
        return res;

    }
}