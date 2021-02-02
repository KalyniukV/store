package com.example.store.data;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class OrdersEntry {
    Integer orderId;
    Integer totalPrice;
    String productName;
    Integer quantity;
    String orderCreated;
}
