package com.example.Military_Asset_Management.Contollers;


import com.example.Military_Asset_Management.Entities.ItemAssignment;
import com.example.Military_Asset_Management.Repositories.ItemAssignmentRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class ItemAssignmentController {

    @Autowired
    private ItemAssignmentRepo itemAssignmentRepo;

    @PostMapping("/save/itemAssignment")
    public ItemAssignment saveItemAssignment(@Valid @RequestBody ItemAssignment itemAssignment){
       return itemAssignmentRepo.save(itemAssignment);
    }

    @GetMapping("/items/assign")
    public List<ItemAssignment> getAssignDetails(){
      return itemAssignmentRepo.findAll();
    }
}
