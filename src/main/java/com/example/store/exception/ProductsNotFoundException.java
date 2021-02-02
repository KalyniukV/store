package com.example.store.exception;

public class ProductsNotFoundException extends Exception {

    public ProductsNotFoundException(Integer id) {
        super("Products not found by id: " + id);
    }
}
