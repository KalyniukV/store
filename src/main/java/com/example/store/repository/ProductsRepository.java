package com.example.store.repository;

import com.example.store.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

    @Query(value = "select " +
            "       p.name as product_name, " +
            "       sum(quantity) as quantity " +
            "from products p " +
            "inner join order_items i on p.id = i.product_id " +
            "group by product_name " +
            "order by quantity",
            nativeQuery = true)
    List<Object[]> getAllProductsInOrder();
}
