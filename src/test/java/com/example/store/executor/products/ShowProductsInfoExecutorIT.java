package com.example.store.executor.products;

import com.example.store.executor.BaseExecutorTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowProductsInfoExecutorIT extends BaseExecutorTest {

    @Override
    protected void setExecutor() {
        executor = new ShowProductsInfoExecutor(productsService);
    }

    @Test
    void execute() {
       createProducts();

        executor.execute();

        String table = "name     | price | status   | " + ls +
                       "_____________________________"  + ls +
                       "cola     | 12    | in_stock | " + ls +
                       "orange   | 34    | in_stock | " + ls +
                       "lemonade | 8     | in_stock | " + ls +
                       "_____________________________";

        assertEquals(table, outputStreamCaptor.toString().trim());
    }
}