package com.example.store.service;

import com.example.store.data.ProductsQuantity;
import com.example.store.domain.Products;
import com.example.store.exception.ProductsNotFoundException;

import java.util.List;

public interface ProductsService {

    Products create(String name, Integer price);

    void remove(Integer id) throws ProductsNotFoundException;

    Products getById(Integer id) throws ProductsNotFoundException;

    List<Products> getAll();

    List<ProductsQuantity> getAllProductsInOrder();
}
