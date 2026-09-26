package com.example.Military_Asset_Management.Contollers;

import com.example.Military_Asset_Management.Entities.Purchase;
import com.example.Military_Asset_Management.Repositories.PurchaseRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class PurchaseController {

    @Autowired
    private PurchaseRepo purchaseRepo;

    @PostMapping("/save/purchase")
    public Purchase savePurchase(@Valid @RequestBody Purchase purchase){
        return purchaseRepo.save(purchase);
    }

    @GetMapping("/all/purchases")
    public List<Purchase> getPurchaseDetails(){
        return purchaseRepo.findAll();
    }
}
