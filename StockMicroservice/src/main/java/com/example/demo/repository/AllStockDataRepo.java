package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Stock;

public interface AllStockDataRepo extends JpaRepository<Stock, Long> {
	
	
	List<Stock> findByExchangeShortName(String exchnageShortName);

	Optional<Stock> findBySymbol(String symbol);

	 @Query("SELECT s FROM Stock s WHERE s.exchangeShortName = 'NASDAQ' AND s.type = 'stock' ORDER BY s.price DESC")
	    List<Stock> findTop20StocksByPrice(Pageable pageable);

	    default List<Stock> findTop20Stocks() {
	        Pageable pageable = PageRequest.of(0, 21);	
	        return findTop20StocksByPrice(pageable);
	    }

}
