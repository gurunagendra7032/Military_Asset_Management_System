package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepo extends JpaRepository<Transfer,Integer> {
}
