package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Portfolio;
import com.example.demo.entity.Stock;

public interface PortfolioService {
	
	public List<Portfolio> getAllUserPortfolio(int userId);
	
	public String createUserPortfolio(Portfolio portfolio);
	
	public List<Portfolio> updateUserPortfolio(int portfolioId, Portfolio portfolio);
	
	Portfolio addStocksToPortfolio(int portfolioId, List<Stock> stocks);

}
