package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CompanyProfile;
import com.example.demo.entity.CompanyStockHistory;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockQuote;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ServiceException;
import com.example.demo.feignclient.StockClient;
import com.example.demo.repository.AllStockDataRepo;
import com.example.demo.repository.CompanyProfileRepo;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockClient stockClient;

    @Value("${stock.api.key}")
    private String apiKey;

    @Autowired
    private AllStockDataRepo stockRepository;

    @Autowired
    private CompanyProfileRepo companyProfileRepo;

    @Override
    public String fetchAndSaveAllStocks() {
        try {
            // Fetch all stocks from the API
            List<Stock> allStocks = stockClient.getAllStocks(apiKey);

            // Filter stocks to keep only those with "NASDAQ" as their exchange short name
            List<Stock> nasdaqStocks = allStocks.stream()
                .filter(stock -> "NASDAQ".equalsIgnoreCase(stock.getExchangeShortName()))
                .collect(Collectors.toList());

            for (Stock stock : nasdaqStocks) {
                Optional<Stock> existingStock = stockRepository.findBySymbol(stock.getSymbol());
                if (existingStock.isPresent()) {
                    // Update the existing stock
                    Stock stockToUpdate = existingStock.get();
                    stockToUpdate.setName(stock.getName());
                    stockToUpdate.setPrice(stock.getPrice());
                    stockToUpdate.setExchange(stock.getExchange());
                    stockToUpdate.setExchangeShortName(stock.getExchangeShortName());
                    stockToUpdate.setType(stock.getType());
                    stockRepository.save(stockToUpdate);
                } else {
                    // Save the new stock
                    stockRepository.save(stock);
                }
            }

            return "All NASDAQ Stocks Saved/Updated in Database";
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch and save NASDAQ stocks", e);
        }
    }


    @Override
    public List<Stock> getAllStockInExchnage(String exchange) {
        try {
            return stockRepository.findByExchangeShortName(exchange);
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch stocks for exchange: " + exchange, e);
        }
    }

    @Override
    public CompanyProfile getCompanyProfile(String ticker) {
        try {
            List<CompanyProfile> companyProfiles = stockClient.getCompanyProfile(ticker, apiKey);
            if (companyProfiles == null || companyProfiles.isEmpty()) {
                throw new ResourceNotFoundException("Company profile not found for ticker: " + ticker);
            }
            companyProfileRepo.save(companyProfiles.get(0));
            return companyProfileRepo.findBySymbol(ticker);
            
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch company profile for ticker: " + ticker, e);
        }
    }

    @Override
    public StockQuote getCompanyStockQuote(String ticker) {
        try {
            List<StockQuote> quotes = stockClient.getCompanyStockQuote(ticker, apiKey);
            if (quotes == null || quotes.isEmpty()) {
                throw new ResourceNotFoundException("Stock quote not found for ticker: " + ticker);
            }
            return quotes.get(0);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch stock quote for ticker: " + ticker, e);
        }
    }

    @Override
    public CompanyStockHistory getCompanyStockHistory(String ticker) {
        try {
            CompanyStockHistory history = stockClient.getCompanyStockHistory(ticker, apiKey);
            if (history == null) {
                throw new ResourceNotFoundException("Stock history not found for ticker: " + ticker);
            }
            return history;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch stock history for ticker: " + ticker, e);
        }
    }
}
