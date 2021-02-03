package com.example.store.executor.ordersItems;

import com.example.store.data.OrdersEntry;
import com.example.store.domain.Orders;
import com.example.store.executor.BaseExecutorTest;
import com.example.store.util.DateHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowOrdersEntryExecutorTest extends BaseExecutorTest {

    @Override
    protected void setExecutor() {
        executor = new ShowOrdersEntryExecutor(ordersService);
    }

    @Test
    void execute() {
        createOrders();

        List<OrdersEntry> ordersEntries = ordersService.ordersEntry();
        Integer orderId  = ordersEntries.get(0).getOrderId();
        Integer order2Id = ordersEntries.get(1).getOrderId();
        Integer order3Id = ordersEntries.get(2).getOrderId();

        String orderCreated  = DateHelper.dateWithoutSeconds(ordersEntries.get(0).getOrderCreated());
        String orderCreated2 = DateHelper.dateWithoutSeconds(ordersEntries.get(1).getOrderCreated());
        String orderCreated3 = DateHelper.dateWithoutSeconds(ordersEntries.get(2).getOrderCreated());

        executor.execute();

        String table =  "orderId | totalPrice | productName     | quantity | orderCreated     | \r\n" +
                        "______________________________________________________________________\r\n" +
                        "" + orderId  + "       | 46         | cola,orange     | 22       | " + orderCreated  + " | \r\n" +
                        "" + order2Id + "       | 8          | lemonade        | 3        | " + orderCreated2 + " | \r\n" +
                        "" + order3Id + "       | 42         | lemonade,orange | 10       | " + orderCreated3 + " | \r\n" +
                        "______________________________________________________________________";

        assertEquals(table, outputStreamCaptor.toString().trim());

    }
}