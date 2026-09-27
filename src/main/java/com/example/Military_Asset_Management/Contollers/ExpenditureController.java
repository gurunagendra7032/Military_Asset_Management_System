package com.example.Military_Asset_Management.Contollers;

import com.example.Military_Asset_Management.Entities.Expenditure;
import com.example.Military_Asset_Management.Repositories.ExpenditureRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class ExpenditureController {

    @Autowired
    private ExpenditureRepo expenditureRepo;

    @PostMapping("/save/expenditure")
    public Expenditure saveExpenditure(@Valid @RequestBody Expenditure expenditure){
        return expenditureRepo.save(expenditure);
    }

    @GetMapping("/expenditure/details")
    public List<Expenditure> getExpenditure(){
        return expenditureRepo.findAll();
    }
}
