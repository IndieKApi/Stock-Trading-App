package com.example.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	
	private String symbol;
	private String name;
	private double currentPrice;
	private double purchasePrice;
	private int quantity;
	

}
