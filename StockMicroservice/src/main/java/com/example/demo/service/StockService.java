package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.CompanyProfile;
import com.example.demo.entity.CompanyStockHistory;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockQuote;

public interface StockService {
	
	public String fetchAndSaveAllStocks();

	public List<Stock> getAllStockInExchnage(String exchnage);
	
	public CompanyProfile getCompanyProfile(String ticker);
	
	public StockQuote getCompanyStockQuote(String ticker);
	
	public CompanyStockHistory getCompanyStockHistory(String ticker);
	
}
