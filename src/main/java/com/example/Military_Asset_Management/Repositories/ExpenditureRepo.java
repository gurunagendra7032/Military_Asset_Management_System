package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureRepo extends JpaRepository<Expenditure,Integer> {
}
