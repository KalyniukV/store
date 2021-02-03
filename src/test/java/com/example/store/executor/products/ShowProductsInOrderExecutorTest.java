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

        String table = "name     | quantity | " + ls +
                       "_____________________"  + ls +
                       "lemonade | 8        | " + ls +
                       "cola     | 12       | " + ls +
                       "orange   | 15       | " + ls +
                       "_____________________";

        assertEquals(table, outputStreamCaptor.toString().trim());
    }
}