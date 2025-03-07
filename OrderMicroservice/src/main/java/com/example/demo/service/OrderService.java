package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Order;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockSellDTO;

public interface OrderService {
    Order buyStock(int portfolioId, List<Stock> stock);
    Order sellStock(int portfolioId, String stockSymbol, int stockQuantity);
	List<Order> getUserOrder(int userId);
}
