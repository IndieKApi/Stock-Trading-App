package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyStockHistory {
	
	@JsonProperty("symbol")
	private String symbol;
	
	@JsonProperty("historical")
	private List<Historical> history;
}
