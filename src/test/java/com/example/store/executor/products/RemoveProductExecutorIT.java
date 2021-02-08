package com.example.store.executor.products;

import com.example.store.domain.Products;
import com.example.store.executor.BaseExecutorTest;
import com.example.store.form.RemoveProductForm;
import com.example.store.util.PasswordHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class RemoveProductExecutorIT extends BaseExecutorTest {

    @Override
    protected void setExecutor() {
        executor = new RemoveProductExecutor(productsService, commandAsker);
    }

    @Test
    public void deleteAll() {
        createProducts();

        when(commandAsker.ask(RemoveProductForm.ASK_FOR_REMOVE)).thenReturn("all");
        passwordHelper.when(PasswordHelper::createPassword).thenReturn(1111);
        when(commandAsker.ask(RemoveProductForm.ASK_PASSWORD + "\"" + 1111 + "\"")).thenReturn("1111");

        executor.execute();

        List<Products> productsAfterRemove = productsService.getAll();
        assertEquals(productsAfterRemove.size(), 0);
    }

    @Test
    public void deleteById() {
        createProducts();

        when(commandAsker.ask(RemoveProductForm.ASK_FOR_REMOVE)).thenReturn(productsList.get(0).getId().toString());
        passwordHelper.when(PasswordHelper::createPassword).thenReturn(1111);
        when(commandAsker.ask(RemoveProductForm.ASK_PASSWORD + "\"" + 1111 + "\"")).thenReturn("1111");

        executor.execute();

        List<Products> productsAfterRemove = productsService.getAll();
        assertEquals(productsAfterRemove.size(), 2);
        assertEquals("orange", productsList.get(1).getName());
        assertEquals(34, productsList.get(1).getPrice());
    }

    @Test
    public void deleteWhenWrongPassword() {
        createProducts();

        when(commandAsker.ask(RemoveProductForm.ASK_FOR_REMOVE)).thenReturn("all");
        passwordHelper.when(PasswordHelper::createPassword).thenReturn(1111);
        when(commandAsker.ask(RemoveProductForm.ASK_PASSWORD + "\"" + 1111 + "\"")).thenReturn("wrong password");

        executor.execute();

        List<Products> productsAfterRemove = productsService.getAll();
        assertEquals(productsAfterRemove.size(), 3);
    }
}