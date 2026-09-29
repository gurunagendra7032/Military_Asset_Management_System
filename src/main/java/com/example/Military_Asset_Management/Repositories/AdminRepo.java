package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Integer> {

    Admin findByEmail(String email);
}
