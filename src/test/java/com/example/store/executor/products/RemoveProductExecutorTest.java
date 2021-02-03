package com.example.store.executor.products;

import com.example.store.domain.Products;
import com.example.store.executor.BaseExecutorTest;
import com.example.store.form.RemoveProductForm;
import com.example.store.util.PasswordHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class RemoveProductExecutorTest extends BaseExecutorTest {

    @Override
    protected void setExecutor() {
        executor = new RemoveProductExecutor(productsService, commandAsker);
    }

    @Test
    public void deleteAll() {
        productsService.create("cola", 18);
        productsService.create("orange", 23);

        List<Products> products = productsService.getAll();
        assertEquals(2, products.size());
        assertEquals("cola", products.get(0).getName());
        assertEquals(18, products.get(0).getPrice());
        assertEquals("orange", products.get(1).getName());
        assertEquals(23, products.get(1).getPrice());

        when(commandAsker.ask(RemoveProductForm.ASK_FOR_REMOVE)).thenReturn("all");
        passwordHelper.when(PasswordHelper::createPassword).thenReturn(1111);
        when(commandAsker.ask(RemoveProductForm.ASK_PASSWORD + "\"" + 1111 + "\"")).thenReturn("1111");

        executor.execute();

        List<Products> productsAfterRemove = productsService.getAll();
        assertEquals(productsAfterRemove.size(), 0);
    }

    @Test
    public void deleteById() {
        productsService.create("cola", 18);
        productsService.create("orange", 23);

        List<Products> products = productsService.getAll();
        assertEquals(2, products.size());
        assertEquals("cola", products.get(0).getName());
        assertEquals(18, products.get(0).getPrice());
        assertEquals("orange", products.get(1).getName());
        assertEquals(23, products.get(1).getPrice());

        when(commandAsker.ask(RemoveProductForm.ASK_FOR_REMOVE)).thenReturn(products.get(0).getId().toString());
        passwordHelper.when(PasswordHelper::createPassword).thenReturn(1111);
        when(commandAsker.ask(RemoveProductForm.ASK_PASSWORD + "\"" + 1111 + "\"")).thenReturn("1111");

        executor.execute();

        List<Products> productsAfterRemove = productsService.getAll();
        assertEquals(productsAfterRemove.size(), 1);
        assertEquals("orange", products.get(1).getName());
        assertEquals(23, products.get(1).getPrice());
    }

    @Test
    public void deleteWhenWrongPassword() {
        productsService.create("cola", 18);
        productsService.create("orange", 23);

        List<Products> products = productsService.getAll();
        assertEquals(2, products.size());
        assertEquals("cola", products.get(0).getName());
        assertEquals(18, products.get(0).getPrice());
        assertEquals("orange", products.get(1).getName());
        assertEquals(23, products.get(1).getPrice());

        when(commandAsker.ask(RemoveProductForm.ASK_FOR_REMOVE)).thenReturn("all");
        passwordHelper.when(PasswordHelper::createPassword).thenReturn(1111);
        when(commandAsker.ask(RemoveProductForm.ASK_PASSWORD + "\"" + 1111 + "\"")).thenReturn("wrong password");

        executor.execute();

        List<Products> productsAfterRemove = productsService.getAll();
        assertEquals(productsAfterRemove.size(), 2);
    }
}