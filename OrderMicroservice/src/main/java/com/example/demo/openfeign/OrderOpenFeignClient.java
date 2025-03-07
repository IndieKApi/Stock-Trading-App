package com.example.demo.openfeign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Inventory;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockSellDTO;


@FeignClient(name = "INVENTORYMICROSERVICE")
public interface OrderOpenFeignClient {
    @PostMapping("/inventory/add-stocks/{portfolioId}")
    Inventory addStockToPortfolio(@PathVariable int portfolioId, @RequestBody List<Stock> stock);

    @DeleteMapping("/inventory/remove-stock/{portfolioId}")
    StockSellDTO removeStockFromPortfolio(@PathVariable int portfolioId, @RequestParam String stockSymbol, @RequestParam int stockQuantity);
}
