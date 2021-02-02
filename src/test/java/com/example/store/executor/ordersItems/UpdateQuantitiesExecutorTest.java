package com.example.store.executor.ordersItems;

import com.example.store.domain.OrderItems;
import com.example.store.domain.Orders;
import com.example.store.domain.Products;
import com.example.store.executor.Executor;
import com.example.store.form.UpdateQuantitiesForm;
import com.example.store.service.OrderItemsService;
import com.example.store.service.OrdersService;
import com.example.store.service.ProductsService;
import com.example.store.util.CommandAsker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Transactional
@SpringBootTest
class UpdateQuantitiesExecutorTest {

    @Autowired
    OrdersService ordersService;
    @Autowired
    ProductsService productsService;
    @Autowired
    OrderItemsService orderItemsService;
    CommandAsker commandAsker = mock(CommandAsker.class);
    Executor executor;

    @BeforeEach
    void setUp() {
        executor = new UpdateQuantitiesExecutor(commandAsker, orderItemsService);
    }

    @Test
    void execute() {
        Orders orders = ordersService.create();
        Products products = productsService.create("cola", 18);
        orderItemsService.create(orders, products, 3);

        List<OrderItems> orderItemsList = orderItemsService.getAll();
        OrderItems orderItems = orderItemsList.get(0);
        assertEquals(orders.getId(), orderItems.getItems().getOrderId());
        assertEquals(products.getId(), orderItems.getItems().getProductId());
        assertEquals(3, orderItems.getQuantity());

        when(commandAsker.ask(UpdateQuantitiesForm.ASK_FOR_UPDATE))
                .thenReturn(String.format("%s %s %s", orders.getId().toString(), products.getId().toString(), "21"));

        executor.execute();

        OrderItems updateOrderItems = orderItemsService.getAll().get(0);
        assertEquals(21, updateOrderItems.getQuantity());
    }
}