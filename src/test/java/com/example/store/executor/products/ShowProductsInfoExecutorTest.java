package com.example.store.executor.products;

import com.example.store.executor.BaseExecutorTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowProductsInfoExecutorTest extends BaseExecutorTest {

    @Override
    protected void setExecutor() {
        executor = new ShowProductsInfoExecutor(productsService);
    }

    @Test
    void execute() {
       createProducts();

        executor.execute();

        String table = "name     | price | status   | \r\n" +
                       "_____________________________\r\n" +
                       "cola     | 12    | in_stock | \r\n" +
                       "orange   | 34    | in_stock | \r\n" +
                       "lemonade | 8     | in_stock | \r\n" +
                       "_____________________________";

        assertEquals(table, outputStreamCaptor.toString().trim());
    }
}