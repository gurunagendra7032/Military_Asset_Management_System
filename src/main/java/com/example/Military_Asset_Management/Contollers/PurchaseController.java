package com.example.Military_Asset_Management.Contollers;

import com.example.Military_Asset_Management.Entities.Base;
import com.example.Military_Asset_Management.Entities.Purchase;
import com.example.Military_Asset_Management.Entities.User;
import com.example.Military_Asset_Management.Repositories.BaseRepo;
import com.example.Military_Asset_Management.Repositories.PurchaseRepo;
import com.example.Military_Asset_Management.Repositories.UserRepo;
import com.example.Military_Asset_Management.Services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://militaryassetmanagementfrontend.vercel.app")
public class PurchaseController {

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BaseRepo baseRepo;

    @PostMapping("/purchase/save")
    public Purchase savePurchase(@Valid @RequestBody Purchase purchase, Authentication authentication){
        String email= authentication.getName();
        User user = userRepo.findByUserEmail(email);
        Base base=user.getBase();
        purchase.setBase(base);
        return purchaseRepo.save(purchase);
    }

    @GetMapping("/purchases/{equipmentType}/{date}")
    public List<Purchase> getPurchaseDetails(
            @PathVariable String equipmentType, @PathVariable LocalDate date,Authentication authentication)
    {
        String email=authentication.getName();
        User user=userRepo.findByUserEmail(email);
        Base base=baseRepo.findById(user.getBase().getId()).orElseThrow();
        Integer id= base.getId();

        return purchaseRepo.findByBaseIdAndEquipmentTypeAndPurchaseDateBefore(id,equipmentType,date);
    }




}
