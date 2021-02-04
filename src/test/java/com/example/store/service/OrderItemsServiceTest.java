package com.example.store.service;

import com.example.store.domain.OrderItems;
import com.example.store.domain.Orders;
import com.example.store.domain.Products;
import com.example.store.repository.OrderItemsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


class OrderItemsServiceTest {

    OrderItemsRepository orderItemsRepository;
    OrderItemsService orderItemsService;

    @BeforeEach
    void setUp() {
        orderItemsRepository = mock(OrderItemsRepository.class);
        orderItemsService = new OrderItemsServiceImpl(orderItemsRepository);
    }

    @Test
    void create() {
        Orders orders = new Orders();
        orders.setId(1);
        Products products = new Products();
        products.setId(2);

        orderItemsService.create(orders, products, anyInt());
        verify(orderItemsRepository, times(1)).save(any(OrderItems.class));
    }

    @Test
    void updateQuantity() {
        OrderItems orderItems = new OrderItems();
        orderItems.setQuantity(1);

        OrderItems updateOrderItems = new OrderItems();
        updateOrderItems.setQuantity(2);

        when(orderItemsRepository.findByItems_OrderIdAndItems_ProductId(anyInt(), anyInt())).thenReturn(orderItems);
        when(orderItemsRepository.save(orderItems)).thenReturn(updateOrderItems);

        orderItemsService.updateQuantity(anyInt(), anyInt(), 2);

        verify(orderItemsRepository, times(1)).save(orderItems);

        assertEquals(2, updateOrderItems.getQuantity());
    }

}