package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockSellDTO {

	private int inventoryId;

	private int userId;
	
	private double sellValue;
	
	private String stockSymbol;
	
	private String stockname;
	
	private int stockQuantity;
	
	private double stocksellPrice;

	public void setStocksellPrice(Double price) {
		this.stocksellPrice = price;
		
	}
		
}
