package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Portfolio;
import com.example.demo.entity.Stock;
import com.example.demo.repository.PortfolioRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PortfolioServicImpl implements PortfolioService {

	private PortfolioRepo portfolioRepo;

	@Override
	public List<Portfolio> getAllUserPortfolio(int userId) {
		return portfolioRepo.findByUserId(userId);

	}

	@Override
	@Transactional
	public String createUserPortfolio(Portfolio portfolio) {

		portfolioRepo.save(portfolio);
		return "Created Successfully";
	}

	@Override
	@Transactional
	public List<Portfolio> updateUserPortfolio(int portfolioId, Portfolio portfolio) {

		Portfolio existingPortfolio = portfolioRepo.findById(portfolioId).get();

		existingPortfolio.setPortfolioName(portfolio.getPortfolioName());
		existingPortfolio.setTotalInvestment(portfolio.getTotalInvestment());
		existingPortfolio.setCurrentValue(portfolio.getCurrentValue());

		// set stocks
		List<Stock> stocks = existingPortfolio.getStocks();

		for (int i = 0; i < portfolio.getStocks().size(); i++) {
			stocks.add(portfolio.getStocks().get(i));
		}
		
		
		existingPortfolio.getStocks().get(0).setSymbol(portfolio.getStocks().get(0).getSymbol());
		
		existingPortfolio.setCreatedAt(portfolio.getCreatedAt());

		portfolioRepo.save(existingPortfolio);

		return portfolioRepo.findByUserId(portfolio.getUserId());
	}
	
	
	@Override
    @Transactional
    public Portfolio addStocksToPortfolio(int portfolioId, List<Stock> stocks) {
        
		
        Portfolio existingPortfolio = portfolioRepo.findById(portfolioId).get();
        

        existingPortfolio.getStocks().addAll(stocks);

        
        return portfolioRepo.save(existingPortfolio);
    }
}
