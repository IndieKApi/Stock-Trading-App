package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CompanyInfoResponse;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockSellDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.openfeign.InventoryOpenFeign;
import com.example.demo.repository.StockInventoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	private InventoryOpenFeign inventoryOpenFeign;

	private StockInventoryRepository inventoryRepository;

	@Override
	public List<Inventory> getAllUserInventories(int userId) {
		try {
			List<Inventory> userInventory = inventoryRepository.findByUserId(userId);

			if (userInventory.isEmpty()) {
				throw new ResourceNotFoundException("No inventory found for user with ID: " + userId);
			}

			return userInventory;
		} catch (Exception e) {
			throw new RuntimeException("Failed to fetch inventory for user with ID: " + userId, e);
		}
	}

	@Override
	public Inventory createUserInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	@Override
	public Inventory addStocksToInventory(int inventoryId, List<Stock> newStocks) {
		Inventory existingInventory = inventoryRepository.findById(inventoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not found for id: " + inventoryId));

		List<Stock> existingStocks = existingInventory.getStocks();

		for (Stock newStock : newStocks) {
			boolean stockExists = false;

			for (Stock existingStock : existingStocks) {
				if (existingStock.getSymbol().equals(newStock.getSymbol())) {
					// Update the existing stock's quantity and prices
					existingStock.setQuantity(existingStock.getQuantity() + newStock.getQuantity());
					existingStock.setPurchasePrice(newStock.getPurchasePrice());

					stockExists = true;
					break;
				}
			}

			if (!stockExists) {

				existingStocks.add(newStock);
			}
		}

		long totalInvestment = 0;

		for (Stock stock : existingStocks) {
			totalInvestment += stock.getPurchasePrice() * stock.getQuantity();
		}

		// Update the inventory values
		existingInventory.setTotalInvestment(totalInvestment);

		return inventoryRepository.save(existingInventory);
	}

	@Override
	public StockSellDTO removeStockFromInventory(int inventoryId, String stockSymbol, int quantity) {
		
//		System.out.println("Stocks quantity to sell " + quantity + " Stock Symbol is " + stockSymbol);
		
		Inventory existingInventory = inventoryRepository.findById(inventoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not found for id: " + inventoryId));
		
//		System.out.println("exixting of Inventoryid "+ inventoryId + " ------------ " + existingInventory);
		
		CompanyInfoResponse stockCurrentInfo = inventoryOpenFeign.getCompanyInfo(stockSymbol);

		List<Stock> stocks = existingInventory.getStocks();
		
		Double sellValue = 0.0;
		
		boolean stockNotFound = true;

		for (Stock stock : stocks) {
			
//			System.out.println(stock);
			
			if (stock.getSymbol().equals(stockSymbol)) {
				
				stockNotFound = false;
				
				System.out.println(stock.getQuantity() + " this is my stock quantity and this is quantity to sell "+ quantity);
				
				if (stock.getQuantity() < quantity) {
					throw new IllegalArgumentException("Insufficient stock quantity to Sell");
				}
				

				sellValue = quantity * (stockCurrentInfo.getPrice());

				if (stock.getQuantity() == quantity) {
					// Remove the stock completely
					stocks.remove(stock);
				} else {
					// Update the stock quantity
					stock.setQuantity(stock.getQuantity() - quantity);
				}

				break;
			}
			
		}
		
		if(stockNotFound)
		{
			throw new IllegalArgumentException("Insufficient stock quantity to Sell");
		}

		long totalInvestment = 0;

		for (Stock stock : stocks) {
			totalInvestment += stock.getPurchasePrice() * stock.getQuantity();
		}

		// Update the inventory values
		existingInventory.setTotalInvestment(totalInvestment);

		Inventory userInventory = inventoryRepository.save(existingInventory);

		StockSellDTO stockSellObj = new StockSellDTO();

		stockSellObj.setInventoryId(inventoryId);
		stockSellObj.setUserId(userInventory.getUserId());
		stockSellObj.setSellValue(sellValue);
		stockSellObj.setStockSymbol(stockSymbol);
		stockSellObj.setStockname(stockCurrentInfo.getCompanyName());
		stockSellObj.setStockQuantity(quantity);
		stockSellObj.setStocksellPrice(stockCurrentInfo.getPrice());

		return stockSellObj;
	}

	@Override
	public Inventory updateUserInventory(int id, Inventory inventory) {
		Inventory existingInventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not found for id: " + id));

		existingInventory.setTotalInvestment(inventory.getTotalInvestment());

		return inventoryRepository.save(existingInventory);
	}

}
