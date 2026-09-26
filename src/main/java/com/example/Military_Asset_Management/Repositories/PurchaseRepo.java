package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase,Integer> {
}
