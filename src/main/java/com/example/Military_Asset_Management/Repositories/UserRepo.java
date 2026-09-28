package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUserEmail(String email);

}
