package com.example.demo.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inventory {
   
    private int inventoryId;

    private int userId;
    private long totalInvestment;

    private List<Stock> stocks;

}
