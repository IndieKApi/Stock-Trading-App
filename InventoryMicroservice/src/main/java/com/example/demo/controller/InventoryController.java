package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Inventory;
import com.example.demo.entity.InventoryCreation;
import com.example.demo.entity.Stock;
import com.example.demo.entity.StockSellDTO;
import com.example.demo.service.InventoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Inventory>> getAllUserInventories(@PathVariable int userId) {
        List<Inventory> inventories = inventoryService.getAllUserInventories(userId);
        return ResponseEntity.ok(inventories);
    }
    

    @PostMapping("/new")
    public ResponseEntity<InventoryCreation> createUserInventory(@RequestBody Inventory inventory) {
        Inventory createdInventory = inventoryService.createUserInventory(inventory);
    	
    	InventoryCreation creation = new InventoryCreation();
    	creation.setMsg("Created Successfully");
        creation.setInventoryId(createdInventory.getInventoryId());
        
    	return ResponseEntity.ok(creation);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Inventory> updateUserInventory(@PathVariable int id, @RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.updateUserInventory(id,inventory));
    }

    @PostMapping("/add-stocks/{inventoryId}")
    public ResponseEntity<Inventory> addStocksToInventory(@PathVariable int inventoryId, @RequestBody List<Stock> stocks) {
        Inventory updatedInventory = inventoryService.addStocksToInventory(inventoryId, stocks);
        return ResponseEntity.ok(updatedInventory);
    }

    @DeleteMapping("/remove-stock/{inventoryId}")
    public ResponseEntity<StockSellDTO> removeStockFromInventory(@PathVariable int inventoryId, @RequestParam String stockSymbol, @RequestParam int stockQuantity) {
        StockSellDTO sellStockInfo = inventoryService.removeStockFromInventory(inventoryId, stockSymbol, stockQuantity);
        return ResponseEntity.ok(sellStockInfo);
    }
}
