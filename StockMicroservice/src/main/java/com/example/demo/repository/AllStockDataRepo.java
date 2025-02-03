package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Stock;

public interface AllStockDataRepo extends JpaRepository<Stock, Long>{
	
	List<Stock> findByExchangeShortName(String exchnageShortName);
	
	Optional<Stock> findBySymbol(String symbol);

}
