package com.example.store.executor.ordersItems;

import com.example.store.domain.OrderItems;
import com.example.store.domain.Orders;
import com.example.store.domain.Products;
import com.example.store.executor.BaseExecutorTest;
import com.example.store.form.UpdateQuantitiesForm;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class UpdateQuantitiesExecutorTest extends BaseExecutorTest {

    @Override
    protected void setExecutor() {
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