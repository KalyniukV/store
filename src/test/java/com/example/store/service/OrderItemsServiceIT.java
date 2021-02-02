package com.example.store.service;

import com.example.store.domain.OrderItems;
import com.example.store.domain.Orders;
import com.example.store.domain.Products;
import com.example.store.domain.ProductsStatus;
import com.example.store.repository.OrderItemsRepository;
import com.example.store.repository.OrdersRepository;
import com.example.store.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class OrderItemsServiceIT {

    @Autowired
    OrderItemsService orderItemsService;
    @Autowired
    OrderItemsRepository orderItemsRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductsRepository productsRepository;

    private Orders orders;
    private Products products;

    @BeforeEach
    void setUp() {
        orders = ordersRepository.save(Orders.builder()
                .status("created")
                .userId(8)
                .build());

        products = productsRepository.save(Products.builder()
                .name("orange")
                .price(43)
                .status(ProductsStatus.in_stock)
                .build());
    }

    @Test
    @Rollback
    void create() {
        OrderItems orderItems = orderItemsService.create(orders, products, 11);
        assertNotNull(orderItems);
        assertEquals(orderItems.getItems().getOrderId(), orders.getId());
        assertEquals(orderItems.getItems().getProductId(), products.getId());
        assertEquals(orderItems.getItems().getProductId(), products.getId());
        assertEquals(orderItems.getQuantity(), 11);
    }

    @Test
    @Rollback
    void updateQuantity() {
        orderItemsService.create(orders, products, 11);

        orderItemsService.updateQuantity(orders.getId(), products.getId(), 123);

        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        OrderItems orderItems = orderItemsList.get(0);
        assertEquals(orderItems.getItems().getOrderId(), orders.getId());
        assertEquals(orderItems.getItems().getProductId(), products.getId());
        assertEquals(orderItems.getItems().getProductId(), products.getId());
        assertEquals(orderItems.getQuantity(), 123);
    }

}