package com.example.store.executor.products;

import com.example.store.domain.Products;
import com.example.store.executor.Executor;
import com.example.store.form.CreateProductForm;
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
class CreateProductExecutorTest {
    @Autowired
    ProductsService productsService;

    CommandAsker commandAsker = mock(CommandAsker.class);
    MockedStatic<PasswordHelper> passwordHelperMockedStatic = mockStatic(PasswordHelper.class);
    Executor executor;

    @BeforeEach
    void setUp() {
        executor = new CreateProductExecutor(productsService, commandAsker);
    }

    @AfterEach
    void tearDown() {
        passwordHelperMockedStatic.close();
    }

    @Test
    void execute() {
        when(commandAsker.ask(CreateProductForm.ASK_NAME)).thenReturn("cup");
        when(commandAsker.ask(CreateProductForm.ASK_PRICE)).thenReturn("45");
        executor.execute();

        List<Products> products = productsService.getAll();
        assertEquals(1, products.size());
        assertEquals("cup", products.get(0).getName());
        assertEquals(45, products.get(0).getPrice());
    }
}