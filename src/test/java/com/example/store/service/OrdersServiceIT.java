package com.example.store.service;

import com.example.store.domain.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrdersServiceIT {

    @Autowired
    OrdersService ordersService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() {
        Orders orders = ordersService.create();
        assertNotNull(orders);
        assertNotNull(orders.getUserId());
        assertEquals(orders.getStatus(), "created");
//        assertEquals(orders.getCreatedAt(), new Date());
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void ordersEntry() {
    }
}