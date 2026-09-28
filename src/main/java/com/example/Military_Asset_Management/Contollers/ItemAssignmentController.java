package com.example.Military_Asset_Management.Contollers;


import com.example.Military_Asset_Management.Entities.Base;
import com.example.Military_Asset_Management.Entities.ItemAssignment;
import com.example.Military_Asset_Management.Entities.User;
import com.example.Military_Asset_Management.Repositories.BaseRepo;
import com.example.Military_Asset_Management.Repositories.ItemAssignmentRepo;
import com.example.Military_Asset_Management.Repositories.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ItemAssignmentController {

    @Autowired
    private ItemAssignmentRepo itemAssignmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BaseRepo baseRepo;

    @PostMapping("/save/itemAssignment")
    public ItemAssignment saveItemAssignment(@Valid @RequestBody ItemAssignment itemAssignment, Authentication authentication){
        String email=authentication.getName();
        User user=userRepo.findByUserEmail(email);
        Base base=baseRepo.findById(user.getBase().getId()).orElseThrow();
        itemAssignment.setBase(base);
       return itemAssignmentRepo.save(itemAssignment);
    }

    @GetMapping("/items/assign")
    public List<ItemAssignment> getAssignDetails(){
      return itemAssignmentRepo.findAll();
    }
}
