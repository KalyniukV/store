package com.example.store.executor.ordersItems;

import com.example.store.domain.Orders;
import com.example.store.executor.BaseExecutorTest;
import com.example.store.util.DateHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowOrdersInfoExecutorIT extends BaseExecutorTest {

    @Override
    protected void setExecutor() {
        executor = new ShowOrdersInfoExecutor(ordersService);
    }

    @Test
    void execute() {
        createOrders();

        Orders orders = ordersList.get(0);
        Orders orders2 = ordersList.get(1);
        Orders orders3 = ordersList.get(2);

        Integer orderId  = orders.getId();
        Integer order2Id = orders2.getId();
        Integer order3Id = orders3.getId();

        Integer orderUserId  = orders.getUserId();
        Integer order2UserId = orders2.getUserId();
        Integer order3UserId = orders3.getUserId();

        String orderCreated  = DateHelper.dateWithoutSeconds(orders.getCreatedAt());
        String orderCreated2 = DateHelper.dateWithoutSeconds(orders2.getCreatedAt());
        String orderCreated3 = DateHelper.dateWithoutSeconds(orders3.getCreatedAt());

        executor.execute();

        String table =  "id | userId | status  | createdAt        | " + ls +
                        "__________________________________________"  + ls +
                        "" + orderId  + "  | " + orderUserId   + "      | created | " + orderCreated  + " | " + ls +
                        "" + order2Id + "  | " + order2UserId  + "      | created | " + orderCreated2 + " | " + ls +
                        "" + order3Id + "  | " + order3UserId  + "      | created | " + orderCreated3 + " | " + ls +
                        "__________________________________________";

        assertEquals(table, outputStreamCaptor.toString().trim());
    }
}