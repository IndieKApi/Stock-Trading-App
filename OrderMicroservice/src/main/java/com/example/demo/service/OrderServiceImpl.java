package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inventory;
import com.example.demo.entity.Order;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockSellDTO;
import com.example.demo.exception.InvalidOrderException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.openfeign.OrderOpenFeignClient;
import com.example.demo.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	
	private OrderOpenFeignClient inventoryClient;

	private OrderRepository orderRepository;

	@Override
	public Order buyStock(int portfolioId, List<Stock> stock) {
		try {
			Inventory inventory = inventoryClient.addStockToPortfolio(portfolioId, stock);

			// Save the order details
			Order order = new Order();
			order.setUserId(inventory.getUserId());
			order.setPortfolioId(portfolioId);
			order.setStockSymbol(stock.get(0).getSymbol());
			order.setStockName(stock.get(0).getName());
			order.setOrderType("BUY");
			order.setQuantity(stock.get(0).getQuantity());
			order.setPrice(stock.get(0).getPurchasePrice());
			order.setOrderValue(stock.get(0).getQuantity() * stock.get(0).getPurchasePrice());
			order.setTimestamp(LocalDateTime.now());

			return orderRepository.save(order);
		} catch (Exception e) {
			throw new InvalidOrderException("Failed to buy stock: " + e.getMessage());
		}
	}

	@Override
	public Order sellStock(int portfolioId, String stockSymbol, int stockQuantity) {
		try {
			
//			System.out.println(stockQuantity);
			StockSellDTO dto = inventoryClient.removeStockFromPortfolio(portfolioId, stockSymbol, stockQuantity);
			
			System.out.println(dto);
			// Save the order details
			Order order = new Order();
			order.setUserId(dto.getUserId());
			order.setPortfolioId(portfolioId);
			order.setStockSymbol(stockSymbol);
			order.setStockName(dto.getStockname());
			order.setOrderType("SELL");
			order.setQuantity(stockQuantity);
			order.setPrice(dto.getStocksellPrice());
			order.setOrderValue(dto.getSellValue());
			order.setTimestamp(LocalDateTime.now());

			return orderRepository.save(order);
		} catch (Exception e) {
			throw new InvalidOrderException("Insufficient Quantity to sell stock");
		}
	}

	@Override
	public List<Order> getUserOrder(int userId) {
		List<Order> orders = orderRepository.findByUserId(userId);
		if (orders.isEmpty()) {
			throw new OrderNotFoundException("No orders found for user ID: " + userId);
		}
		return orders;
	}
}
