package com.example.store.service;

import com.example.store.domain.Orders;
import com.example.store.exception.OrdersNotFoundException;
import com.example.store.repository.OrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class OrdersServiceTest {

    OrdersRepository ordersRepository;
    OrdersService ordersService;

    @BeforeEach
    void setUp() {
        ordersRepository = mock(OrdersRepository.class);
        ordersService = new OrdersServiceImpl(ordersRepository);
    }

    @Test
    void create() {
        ordersService.create();
        verify(ordersRepository, times(1)).save(any(Orders.class));
    }

    @Test
    void getById() throws OrdersNotFoundException {
        Optional<Orders> optionalOrders = Optional.of(new Orders());
        when(ordersRepository.findById(anyInt())).thenReturn(optionalOrders);

        ordersService.getById(anyInt());
        verify(ordersRepository, times(1)).findById(anyInt());
    }

    @Test
    void getAll() {
        ordersService.getAll();
        verify(ordersRepository, times(1)).findAll();
    }

    @Test
    void ordersEntry() {
        ordersService.ordersEntry();
        verify(ordersRepository, times(1)).ordersEntry();
    }
}