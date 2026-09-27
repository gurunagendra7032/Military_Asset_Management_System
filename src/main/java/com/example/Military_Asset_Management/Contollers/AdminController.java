package com.example.Military_Asset_Management.Contollers;

import com.example.Military_Asset_Management.Repositories.PurchaseRepo;
import com.example.Military_Asset_Management.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/openBalance/{id}/{equipmentType}/{date}")
    public Integer getOpeningBalance(
            @PathVariable Integer id, @PathVariable String equipmentType, @PathVariable LocalDate date)
    {

        return adminService.getOpeningBalance(id, equipmentType, date);
    }


    @GetMapping("/closingBalance/{id}/{equipmentType}/{date}")
    public Integer getClosingBalance(
            @PathVariable Integer id, @PathVariable String equipmentType,@PathVariable LocalDate date)
    {
        return adminService.closingBalance(id,equipmentType,date);
    }


    @GetMapping("/netMovement/{id}/{equipmentType}/{date}")
    public Integer getNetMovement(
            @PathVariable Integer id, @PathVariable String equipmentType,@PathVariable LocalDate date)
    {
        return adminService.NetMovement(id, equipmentType, date);
    }
}
