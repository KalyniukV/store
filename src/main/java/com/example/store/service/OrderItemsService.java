package com.example.store.service;

import com.example.store.domain.OrderItems;
import com.example.store.domain.Orders;
import com.example.store.domain.Products;

import java.util.List;


public interface OrderItemsService {

    OrderItems create(Orders orders, Products product, Integer quantity);

    void updateQuantity(Integer orderId, Integer productId, Integer quantity);

    List<OrderItems> getAll();

}
