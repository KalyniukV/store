package com.example.store.executor.products;

import com.example.store.domain.Products;
import com.example.store.executor.Executor;
import com.example.store.form.CreateProductForm;
import com.example.store.form.RemoveProductForm;
import com.example.store.service.ProductsService;
import com.example.store.util.CommandAsker;
import com.example.store.util.PasswordHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Transactional
@SpringBootTest
class RemoveProductExecutorTest {

    @Autowired
    ProductsService productsService;

    CommandAsker commandAsker = mock(CommandAsker.class);
    MockedStatic<PasswordHelper> passwordHelperMockedStatic = mockStatic(PasswordHelper.class);
    Executor executor;

    @BeforeEach
    void setUp() {
        executor = new RemoveProductExecutor(productsService, commandAsker);
    }

    @AfterEach
    void tearDown() {
        passwordHelperMockedStatic.close();
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
        passwordHelperMockedStatic.when(PasswordHelper::createPassword).thenReturn(1111);
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
        passwordHelperMockedStatic.when(PasswordHelper::createPassword).thenReturn(1111);
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
        passwordHelperMockedStatic.when(PasswordHelper::createPassword).thenReturn(1111);
        when(commandAsker.ask(RemoveProductForm.ASK_PASSWORD + "\"" + 1111 + "\"")).thenReturn("wrong password");

        executor.execute();

        List<Products> productsAfterRemove = productsService.getAll();
        assertEquals(productsAfterRemove.size(), 2);
    }
}