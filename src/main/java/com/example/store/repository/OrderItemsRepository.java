package com.example.store.repository;

import com.example.store.domain.OrderItems;
import com.example.store.domain.OrderItemsPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, OrderItemsPK> {

    OrderItems findByItems_OrderIdAndItems_ProductId(Integer orderId, Integer productId);

}
