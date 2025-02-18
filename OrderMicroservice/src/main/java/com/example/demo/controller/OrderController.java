package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Order;
import com.example.demo.entity.Stock;
import com.example.demo.service.OrderService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @PostMapping("/buy-stock/{portfolioId}")
    public ResponseEntity<Order> buyStock(@PathVariable int portfolioId, @RequestBody List<Stock> stock) {
        return ResponseEntity.ok(orderService.buyStock(portfolioId, stock));
    }

    @PostMapping("/sell-stock/{portfolioId}")
    public ResponseEntity<Order> sellStock(@PathVariable int portfolioId, @RequestParam String stockSymbol, @RequestParam int stockQuantity) {
        
        return ResponseEntity.ok(orderService.sellStock(portfolioId, stockSymbol, stockQuantity));
    }
    
    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Order>> getUserOrder(@PathVariable int userId)
    {
    	return ResponseEntity.ok(orderService.getUserOrder(userId));
    }
}
