package com.example.demo.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CompanyProfile;
import com.example.demo.entity.CompanyStockHistory;
import com.example.demo.entity.Historical;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockQuote;
import com.example.demo.service.StockService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
//@CrossOrigin("http://localhost:5173")
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
	
	@GetMapping("/currentprice/{ticker}")
    public ResponseEntity<Double> getCurrentPriceOfStock(@PathVariable String ticker)
    {
    	return ResponseEntity.ok(stockService.getCurrentPrice(ticker));
    }
    
	@GetMapping("/company/{info}")
    public ResponseEntity<CompanyProfile> fetchCompanyInfo(@PathVariable String info) {
        CompanyProfile profile = stockService.getCompanyProfile(info);
//		
//		CompanyProfile sampleProfile = new CompanyProfile(
//	            "AAPL",
//	            178.72,
//	            1.286802,
//	            58405568L,
//	            2794144143933L,
//	            0.96,
//	            "124.17-198.23",
//	            -0.13,
//	            "Apple Inc.",
//	            "USD",
//	            "0000320193",
//	            "US0378331005",
//	            "037833100",
//	            "NASDAQ Global Select",
//	            "NASDAQ",
//	            "Consumer Electronics",
//	            "https://www.apple.com",
//	            "Apple Inc. designs, manufactures, and markets smartphones, personal computers, tablets, wearables, and accessories worldwide. It also sells various related services...",
//	            "Mr. Timothy D. Cook",
//	            "Technology",
//	            "US",
//	            "164000",
//	            "408 996 1010",
//	            "One Apple Park Way",
//	            "Cupertino",
//	            "CA",
//	            "95014",
//	            4.15176,
//	            150.082,
//	            "https://financialmodelingprep.com/image-stock/AAPL.png",
//	            "1980-12-12",
//	            false,
//	            false,
//	            false,
//	            false,
//	            false
//	        );
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/quote/{ticker}")
    public ResponseEntity<StockQuote> fetchCompanyStockQuote(@PathVariable String ticker) {
        StockQuote quote = stockService.getCompanyStockQuote(ticker);
    	
//    	StockQuote sampleQuote = new StockQuote(
//                100L,
//    			"AAPL",
//                "Apple Inc.",
//                145.775,
//                0.32,
//                0.465,
//                143.9,
//                146.71,
//                179.61,
//                124.17,
//                2306437439846L,
//                140.8724,
//                147.18594,
//                "NASDAQ",
//                42478176L,
//                73638864L,
//                144.38,
//                145.31,
//                5.89,
//                24.75,
//                "2023-04-26T10:59:00.000+0000",
//                15821899776L,
//                1677790773L
//            );
        return ResponseEntity.ok(quote);
    }

    @GetMapping("/history/{ticker}")
    public ResponseEntity<CompanyStockHistory> fetchCompanyStockHistory(@PathVariable String ticker) {
        
    	
    	CompanyStockHistory history = stockService.getCompanyStockHistory(ticker);
        
//        CompanyStockHistory sampleData = new CompanyStockHistory();
//        
//        sampleData.setSymbol(ticker);
//        sampleData.setHistory(Arrays.asList(
//                new Historical("2023-10-09", 179.00, 180.75, 178.10, 180.55, 180.55, 20765432L, 20765432L, 1.65, 0.92750, 179.72, "October 09, 23", 0.0092750),
//                new Historical("2023-10-08", 177.90, 179.50, 176.80, 178.90, 178.90, 22543211L, 22543211L, 1.05, 0.59020, 178.40, "October 08, 23", 0.0059020),
//                new Historical("2023-10-07", 176.75, 178.00, 175.50, 177.85, 177.85, 19987654L, 19987654L, 1.32, 0.74745, 177.12, "October 07, 23", 0.0074745),
//                new Historical("2023-10-06", 173.8, 176.61, 173.18, 176.53, 176.53, 21712747L, 21712747L, 2.73, 1.57077, 175.44, "October 06, 23", 0.0157077),
//                new Historical("2023-10-05", 173.79, 175.45, 172.68, 174.91, 174.91, 48251046L, 48251046L, 1.12, 0.64446, 174.23, "October 05, 23", 0.0064446)
//        ));
        
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/top20")
    public ResponseEntity<List<Stock>> fetchTop20Stocks()
    {
    	List<Stock> top20Stocks = stockService.getTop20Stocks();
    	return ResponseEntity.ok(top20Stocks);
    }
    


}
