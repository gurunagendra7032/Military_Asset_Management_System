package com.example.Military_Asset_Management.Contollers;

import com.example.Military_Asset_Management.Entities.Base;
import com.example.Military_Asset_Management.Entities.Expenditure;
import com.example.Military_Asset_Management.Entities.Transfer;
import com.example.Military_Asset_Management.Entities.User;
import com.example.Military_Asset_Management.Repositories.BaseRepo;
import com.example.Military_Asset_Management.Repositories.TransferRepo;
import com.example.Military_Asset_Management.Repositories.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://militaryassetmanagementfrontend.vercel.app/")
public class TransferController {

    @Autowired
    private TransferRepo transferRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BaseRepo baseRepo;

    @PostMapping("/transfer/save")
    public Transfer saveTransfer(@Valid @RequestBody Transfer transfer, Authentication authentication){
        String email= authentication.getName();
        User user = userRepo.findByUserEmail(email);
        Base base = user.getBase();
        transfer.setFromBase(base);
        Base getToBase=transfer.getToBase();
        Integer id= getToBase.getId();
        Base toBase=baseRepo.findById(id).orElseThrow(() -> new RuntimeException("Base not found"));
        transfer.setToBase(toBase);
        return transferRepo.save(transfer);
    }

    @GetMapping("/transfers/{equipmentType}/{date}")
    public List<Transfer> getExpenditure(
            @PathVariable String equipmentType, @PathVariable LocalDateTime date, Authentication authentication)
    {
        String email = authentication.getName();
        User user=userRepo.findByUserEmail(email);
        Base base=baseRepo.findById(user.getBase().getId()).orElseThrow();
        Integer id= base.getId();
        return transferRepo.findByFromBaseIdAndEquipmentTypeAndTransferDateBetween(id,equipmentType,date,date);
    }
}
