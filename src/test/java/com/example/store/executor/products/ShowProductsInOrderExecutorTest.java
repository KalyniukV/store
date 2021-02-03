package com.example.store.executor.products;

import com.example.store.executor.BaseExecutorTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowProductsInOrderExecutorTest extends BaseExecutorTest {

    @Override
    protected void setExecutor() {
        executor = new ShowProductsInOrderExecutor(productsService);
    }

    @Test
    void execute() {
        createOrders();

        executor.execute();

        String table = "name     | quantity | \r\n" +
                       "_____________________\r\n" +
                       "lemonade | 8        | \r\n" +
                       "cola     | 12       | \r\n" +
                       "orange   | 15       | \r\n" +
                       "_____________________";

        assertEquals(table, outputStreamCaptor.toString().trim());
    }
}