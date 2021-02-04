package com.example.store.service;

import com.example.store.domain.Products;
import com.example.store.exception.ProductsNotFoundException;
import com.example.store.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;


public class ProductServiceTest {

    ProductsRepository productsRepository;
    ProductsService productsService;

    @BeforeEach
    void setUp() {
        productsRepository = mock(ProductsRepository.class);
        productsService = new ProductsServiceImpl(productsRepository);
    }

    @Test
    void create() {
        productsService.create(anyString(), 1);
        verify(productsRepository, times(1)).save(any(Products.class));
    }

    @Test
    void remove() throws ProductsNotFoundException {
        Optional<Products> optionalProducts = Optional.of(new Products());
        when(productsRepository.findById(anyInt())).thenReturn(optionalProducts);

        productsService.remove(anyInt());
        verify(productsRepository, times(1)).delete(optionalProducts.get());
    }

    @Test
    void getById() throws ProductsNotFoundException {
        Optional<Products> optionalProducts = Optional.of(new Products());
        when(productsRepository.findById(anyInt())).thenReturn(optionalProducts);

        productsService.getById(anyInt());
        verify(productsRepository, times(1)).findById(anyInt());
    }

    @Test
    void getAll() {
        productsService.getAll();
        verify(productsRepository, times(1)).findAll();
    }

    @Test
    public void getAllProductsInOrder() {
        productsService.getAllProductsInOrder();
        verify(productsRepository, times(1)).getAllProductsInOrder();
    }
}
