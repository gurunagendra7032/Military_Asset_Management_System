package com.example.Military_Asset_Management.Contollers;

import com.example.Military_Asset_Management.Entities.Base;
import com.example.Military_Asset_Management.Entities.Expenditure;
import com.example.Military_Asset_Management.Entities.User;
import com.example.Military_Asset_Management.Repositories.BaseRepo;
import com.example.Military_Asset_Management.Repositories.ExpenditureRepo;
import com.example.Military_Asset_Management.Repositories.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://militaryassetmanagementfrontend.vercel.app")
public class ExpenditureController {

    @Autowired
    private ExpenditureRepo expenditureRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BaseRepo baseRepo;

    @PostMapping("/save/expenditure")
    public Expenditure saveExpenditure(@Valid @RequestBody Expenditure expenditure, Authentication authentication){

        String email=authentication.getName();
        User user=userRepo.findByUserEmail(email);
        Base base=baseRepo.findById(user.getBase().getId()).orElseThrow();
        expenditure.setBase(base);
        return expenditureRepo.save(expenditure);
    }

    @GetMapping("/expenditure/{equipmentType}/{date}")
    public List<Expenditure> getExpenditure(
            @PathVariable String equipmentType, @PathVariable LocalDate date,Authentication authentication)
    {
        String email = authentication.getName();
        User user=userRepo.findByUserEmail(email);
        Base base=baseRepo.findById(user.getBase().getId()).orElseThrow();
        Integer id= base.getId();
        return expenditureRepo.findByBaseIdAndEquipmentTypeAndDateBefore(id,equipmentType,date);
    }
}
