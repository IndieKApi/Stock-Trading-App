package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Inventory;
import com.example.demo.repository.StockInventoryRepository;
import com.example.demo.service.InventoryServiceImpl;

@SpringBootTest
class InventoryMicroserviceApplicationTests {

	@Mock
    private StockInventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUserInventories() {
        // Prepare test data
        Inventory inventory1 = new Inventory();
        inventory1.setUserId(100);
        

        List<Inventory> userInventories = new ArrayList<>();
        userInventories.add(inventory1);

        // Mock repository response
        when(inventoryRepository.findByUserId(anyInt())).thenReturn(userInventories);

        // Call the method
        List<Inventory> result = inventoryService.getAllUserInventories(100);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals(100, result.get(0).getUserId());

        // Verify interactions
        verify(inventoryRepository, times(1)).findByUserId(anyInt());
    }

}
