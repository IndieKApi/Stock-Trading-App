package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.CompanyProfile;
import com.example.demo.entity.CompanyStockHistory;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockQuote;
import com.example.demo.feignclient.StockClient;
import com.example.demo.repository.AllStockDataRepo;
import com.example.demo.service.StockServiceImpl;

@SpringBootTest
class StockMicroserviceApplicationTests {

	

	@Mock
	private StockClient stockClient;

	@Mock
	private AllStockDataRepo stockRepository;

	@InjectMocks
	private StockServiceImpl stockService;


	@Test
	void testGetAllStockInExchange() {
	    List<Stock> stocks = Arrays.asList(
	        new Stock(1, "AAPL", "Apple Inc.", 130.0, "NASDAQ", "NASDAQ", "Common Stock")
	    );

	    when(stockRepository.findByExchangeShortName(anyString())).thenReturn(stocks);

	    List<Stock> result = stockService.getAllStockInExchnage("NASDAQ");

	    assertEquals(1, result.size());
	    verify(stockRepository, times(1)).findByExchangeShortName("NASDAQ");
	}


}
