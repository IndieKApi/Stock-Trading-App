package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Portfolio;

public interface PortfolioRepo extends JpaRepository<Portfolio, Integer>{
	
	public List<Portfolio> findByUserId(int userId);
	
	public Portfolio findOneByUserId(int userId);

}
