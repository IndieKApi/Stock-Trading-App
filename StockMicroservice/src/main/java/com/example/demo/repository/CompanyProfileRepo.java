package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CompanyProfile;

public interface CompanyProfileRepo extends JpaRepository<CompanyProfile, String>{
	
	public CompanyProfile findBySymbol(String symbol);

}
