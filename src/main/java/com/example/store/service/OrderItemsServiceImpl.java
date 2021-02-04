package com.example.store.service;

import com.example.store.domain.OrderItems;
import com.example.store.domain.OrderItemsPK;
import com.example.store.domain.Orders;
import com.example.store.domain.Products;
import com.example.store.repository.OrderItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    @Override
    public OrderItems create(Orders orders, Products product, Integer quantity) {
        OrderItems orderItems = OrderItems.builder()
                .items(new OrderItemsPK(orders.getId(), product.getId()))
                .orders(orders)
                .products(product)
                .quantity(quantity)
                .build();

        OrderItems saveOrderItems = orderItemsRepository.save(orderItems);

        return saveOrderItems;
    }

    @Override
    public void updateQuantity(Integer orderId, Integer productId, Integer quantity) {
        OrderItems items = orderItemsRepository.findByItems_OrderIdAndItems_ProductId(orderId, productId);
        items.setQuantity(quantity);
        orderItemsRepository.save(items);
    }

    @Override
    public List<OrderItems> getAll() {
        return orderItemsRepository.findAll();
    }
}
