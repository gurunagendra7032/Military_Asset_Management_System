package com.example.Military_Asset_Management.Contollers;

import com.example.Military_Asset_Management.Entities.Base;
import com.example.Military_Asset_Management.Repositories.BaseRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class BaseController {

    @Autowired
    private BaseRepo baseRepo;

    @PostMapping("/save/base")
    public Base saveUser(@Valid @RequestBody Base base){
      return baseRepo.save(base);
    }


    @GetMapping("/get/bases")
    public List<Base> getAllBase(){
        return baseRepo.findAll();
    }
}
