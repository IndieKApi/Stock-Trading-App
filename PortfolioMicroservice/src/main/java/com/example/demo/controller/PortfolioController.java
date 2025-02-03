package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Portfolio;
import com.example.demo.entity.Stock;
import com.example.demo.service.PortfolioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("portfolio")
public class PortfolioController {
	
	
	private PortfolioService portfolioService;
	
	@GetMapping("/all/{userId}")
	public List<Portfolio> getAllUserPortfolio(@PathVariable int userId)
	{
		return portfolioService.getAllUserPortfolio(userId);
	}
	
	@PostMapping("/new")
	public String postUserPortfolio(@RequestBody Portfolio portfolio)
	{	
		return portfolioService.createUserPortfolio(portfolio);
	}
	
	
	@PutMapping("/update/{id}")
	public List<Portfolio> updateUserPortfolio(@PathVariable int id, @RequestBody Portfolio portfolio)
	{
		return portfolioService.updateUserPortfolio(id, portfolio);
	}
	
	@PutMapping("/addStocks/{portfolioId}")
	public Portfolio addStocksInPortfolio(@PathVariable int portfolioId,@RequestBody List<Stock> stocks)
	{
		return portfolioService.addStocksToPortfolio(portfolioId, stocks);
	}
	

}
