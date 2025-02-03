package com.example.demo.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.CompanyInfoResponse;

@FeignClient("STOCKMICROSERVICE")
public interface InventoryOpenFeign {
    
    @GetMapping("/stocks/company/{ticker}")
    CompanyInfoResponse getCompanyInfo(@PathVariable("ticker") String symbol);
}
