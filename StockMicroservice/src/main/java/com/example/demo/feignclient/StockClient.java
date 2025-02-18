package com.example.demo.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.CompanyProfile;
import com.example.demo.entity.CompanyStockHistory;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockQuote;

@FeignClient(name = "stockClient", url = "https://financialmodelingprep.com/api/v3")
public interface StockClient {

	@GetMapping("/stock/list")
    List<Stock> getAllStocks(@RequestParam String apikey);
	
	@GetMapping("/profile/{ticker}")
    List<CompanyProfile> getCompanyProfile(@PathVariable String ticker, @RequestParam String apikey);
	
	@GetMapping("/quote/{ticker}")
	List<StockQuote> getCompanyStockQuote(@PathVariable String ticker, @RequestParam String apikey); 
	
	@GetMapping("/historical-price-full/{ticker}")
	CompanyStockHistory getCompanyStockHistory(@PathVariable String ticker, @RequestParam String apikey);
}
