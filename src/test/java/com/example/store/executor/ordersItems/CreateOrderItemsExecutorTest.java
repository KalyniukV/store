package com.example.store.executor.ordersItems;

import com.example.store.domain.OrderItems;
import com.example.store.domain.Orders;
import com.example.store.domain.Products;
import com.example.store.executor.BaseExecutorTest;
import com.example.store.form.CreateOrderForm;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class CreateOrderItemsExecutorTest extends BaseExecutorTest {

    @Override
    protected void setExecutor() {
        executor = new CreateOrderItemsExecutor(commandAsker, orderItemsService, ordersService, productsService);
    }

    @Test
    void execute() {
        Products cola = productsService.create("cola", 18);
        Products orange = productsService.create("orange", 23);
        List<Products> products = productsService.getAll();
        assertEquals(2, products.size());

        when(commandAsker.ask(CreateOrderForm.ASK_PRODUCTS_ID_QUANTITY))
                .thenReturn(String.format("%s 5, %s 19, 999 18", cola.getId().toString(), orange.getId().toString()));
        when(commandAsker.ask(CreateOrderForm.ASK_CONFIRM)).thenReturn("y");

        executor.execute();

        List<Orders> ordersList = ordersService.getAll();
        assertEquals(1, ordersList.size());
        Orders orders = ordersList.get(0);

        List<OrderItems> orderItemsList = orderItemsService.getAll();
        assertEquals(2, orderItemsList.size());
        OrderItems orderItemsCola = orderItemsList.get(0);
        OrderItems orderItemsOrange = orderItemsList.get(1);

        assertEquals(orders.getId(), orderItemsCola.getItems().getOrderId());
        assertEquals(cola.getId(), orderItemsCola.getItems().getProductId());
        assertEquals(5, orderItemsCola.getQuantity());

        assertEquals(orders.getId(), orderItemsOrange.getItems().getOrderId());
        assertEquals(orange.getId(), orderItemsOrange.getItems().getProductId());
        assertEquals(19, orderItemsOrange.getQuantity());
    }
}