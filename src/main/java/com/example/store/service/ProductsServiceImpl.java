package com.example.store.service;

import com.example.store.data.ProductsQuantity;
import com.example.store.domain.Products;
import com.example.store.domain.ProductsStatus;
import com.example.store.exception.ProductsNotFoundException;
import com.example.store.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "productsService")
@Slf4j
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public Products create(String name, Integer price) {
        Products products = Products.builder()
                                    .name(name)
                                    .price(price)
                                    .status(ProductsStatus.in_stock)
                                    .build();

        Products saveProducts = productsRepository.save(products);

        return saveProducts;
    }

    @Override
    public List<Products> getAll() {
        return productsRepository.findAll();
    }

    @Override
    public Products getById(Integer id) throws ProductsNotFoundException {
        Optional<Products> optionalProducts = productsRepository.findById(id);
        if (optionalProducts.isPresent()) {
            return optionalProducts.get();
        }
        throw new ProductsNotFoundException(id);
    }

    @Override
    public void remove(Integer id) throws ProductsNotFoundException {
        Optional<Products> optionalProducts = productsRepository.findById(id);
        if (optionalProducts.isPresent()) {
            productsRepository.delete(optionalProducts.get());
        } else {
            throw new ProductsNotFoundException(id);
        }
    }

    @Override
    public List<ProductsQuantity> getAllProductsInOrder() {
        List<Object[]> products = productsRepository.getAllProductsInOrder();

        return products.stream()
                .map(o ->
                        new ProductsQuantity(
                                o[0].toString(),
                                Integer.valueOf(o[1].toString()))
                )
                .collect(Collectors.toList());
    }

}
