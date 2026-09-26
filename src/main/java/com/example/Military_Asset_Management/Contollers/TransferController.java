package com.example.Military_Asset_Management.Contollers;

import com.example.Military_Asset_Management.Entities.Transfer;
import com.example.Military_Asset_Management.Repositories.TransferRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
public class TransferController {

    @Autowired
    private TransferRepo transferRepo;

    @PostMapping("/save/transfer")
    public Transfer saveTransfer(@Valid @RequestBody Transfer transfer){
        return transferRepo.save(transfer);
    }

    @GetMapping("/get/transfers")
    public List<Transfer> getTransferDetails(){
       return transferRepo.findAll();
    }
}
