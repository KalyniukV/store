package com.example.store.exception;

public class OrdersNotFoundException extends Exception {

    public OrdersNotFoundException(Integer id) {
        super("Orders not found by id: " + id);
    }
}
