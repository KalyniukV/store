package com.example.store.service;

import com.example.store.data.OrdersEntry;
import com.example.store.domain.Orders;
import com.example.store.exception.OrdersNotFoundException;


import java.util.List;

public interface OrdersService {

    Orders create();

    void remove(Orders orders);

    Orders getById(Integer id) throws OrdersNotFoundException;

    List<Orders> getAll();

    List<OrdersEntry> ordersEntry();

}
