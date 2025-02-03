package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CompanyProfile;
import com.example.demo.entity.CompanyStockHistory;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockQuote;
import com.example.demo.service.StockService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("stocks")
public class StockController {
	
	private StockService stockService;
	
	@GetMapping("/all")
	public ResponseEntity<String> fetchAllStocks() {
	    
	    return ResponseEntity.ok(stockService.fetchAndSaveAllStocks());
	}
	
	@GetMapping("/all/{exchange}")
	public ResponseEntity<List<Stock>> fetchAllStockInNASDAQ(@PathVariable String exchange)
	{	
		List<Stock> stock = stockService.getAllStockInExchnage(exchange);
		return ResponseEntity.ok(stock);
	}
	
	@GetMapping("/company/{info}")
    public ResponseEntity<CompanyProfile> fetchCompanyInfo(@PathVariable String info) {
        CompanyProfile profile = stockService.getCompanyProfile(info);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/quote/{ticker}")
    public ResponseEntity<StockQuote> fetchCompanyStockQuote(@PathVariable String ticker) {
        StockQuote quote = stockService.getCompanyStockQuote(ticker);
        return ResponseEntity.ok(quote);
    }

    @GetMapping("/history/{ticker}")
    public ResponseEntity<CompanyStockHistory> fetchCompanyStockHistory(@PathVariable String ticker) {
        CompanyStockHistory history = stockService.getCompanyStockHistory(ticker);
        return ResponseEntity.ok(history);
    }


}
