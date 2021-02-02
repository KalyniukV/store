package com.example.store.repository;

import com.example.store.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Query(value = "select " +
            "o.id as order_id, " +
            "sum(p.price) as products_total_price, " +
            "group_concat(p.name separator ','), " +
            "sum(oi.quantity), " +
            "o.created_at " +
            "from " +
            "orders o " +
            "inner join order_items oi on o.id = oi.order_id " +
            "inner join products p on oi.product_id = p.id " +
            "group by o.id " +
            "order by o.id",
    nativeQuery = true)
    List<Object[]> ordersEntry();
}
