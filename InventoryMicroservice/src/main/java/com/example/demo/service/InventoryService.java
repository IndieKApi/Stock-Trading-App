package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Inventory;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockSellDTO;

public interface InventoryService {

	List<Inventory> getAllUserInventories(int userId);
    Inventory createUserInventory(Inventory inventory);
    Inventory addStocksToInventory(int inventoryId, List<Stock> stocks);
    StockSellDTO removeStockFromInventory(int inventoryId, String stockSymbol, int quantity);
	Inventory updateUserInventory(int id,Inventory inventory);
}
