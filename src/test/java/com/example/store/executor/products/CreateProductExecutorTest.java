package com.example.store.executor.products;

import com.example.store.domain.Products;
import com.example.store.executor.BaseExecutorTest;
import com.example.store.form.CreateProductForm;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class CreateProductExecutorTest extends BaseExecutorTest {

    @Override
    protected void setExecutor() {
        executor = new CreateProductExecutor(productsService, commandAsker);
    }

    @Test
    void execute() {
        when(commandAsker.ask(CreateProductForm.ASK_NAME)).thenReturn("cola");
        when(commandAsker.ask(CreateProductForm.ASK_PRICE)).thenReturn("45");
        executor.execute();

        List<Products> products = productsService.getAll();
        assertEquals(1, products.size());
        assertEquals("cola", products.get(0).getName());
        assertEquals(45, products.get(0).getPrice());
    }
}