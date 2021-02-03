package com.example.store.service;

import com.example.store.data.ProductsQuantity;
import com.example.store.domain.Orders;
import com.example.store.domain.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class ProductServiceIT {

    @Autowired
    ProductsService productsService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderItemsService orderItemsService;

    @Test
    public void getAllProductsInOrder() {
        Products cola = productsService.create("cola", 12);
        Products orange = productsService.create("orange", 34);

        Orders orders = ordersService.create();
        orderItemsService.create(orders, orange, 10);
        orderItemsService.create(orders, cola, 12);

        Orders orders2 = ordersService.create();
        orderItemsService.create(orders2, orange, 15);

        List<ProductsQuantity> productsQuantities = productsService.getAllProductsInOrder();
        assertEquals(2, productsQuantities.size());

        ProductsQuantity colaQuantity = productsQuantities.get(0);
        assertEquals("cola", colaQuantity.getName());
        assertEquals(12, colaQuantity.getQuantity());

        ProductsQuantity orangeQuantity = productsQuantities.get(1);
        assertEquals("orange", orangeQuantity.getName());
        assertEquals(25, orangeQuantity.getQuantity());
    }
}
