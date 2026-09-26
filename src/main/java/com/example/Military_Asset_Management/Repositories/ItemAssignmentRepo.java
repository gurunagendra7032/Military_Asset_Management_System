package com.example.Military_Asset_Management.Repositories;

import com.example.Military_Asset_Management.Entities.ItemAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemAssignmentRepo extends JpaRepository<ItemAssignment,Integer> {
}
