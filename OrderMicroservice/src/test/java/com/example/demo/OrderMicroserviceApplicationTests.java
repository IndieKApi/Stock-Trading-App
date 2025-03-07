package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderServiceImpl;

@SpringBootTest
class OrderMicroserviceApplicationTests {

	@Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserOrder() {
        // Prepare test data
        Order order1 = new Order();
        order1.setUserId(1);
        Order order2 = new Order();
        order2.setUserId(1);

        List<Order> userOrders = Arrays.asList(order1, order2);

        // Mock repository response
        when(orderRepository.findByUserId(anyInt())).thenReturn(userOrders);

        // Call the method
        List<Order> result = orderService.getUserOrder(1);

        // Verify the result
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getUserId());
        assertEquals(1, result.get(1).getUserId());

       
        verify(orderRepository, times(1)).findByUserId(anyInt());
    }
    
    @Test
    void testforBuyOrder()
    {
    	Order buyOrder = new Order();
    	
    	buyOrder.setOrderType("BUY");
    	
    	assertEquals("BUY",buyOrder.getOrderType());
    	
    }

}
