package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Inventory;

public interface StockInventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findByUserId(int userId);
   
}
