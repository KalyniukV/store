package com.example.store.executor;

import com.example.store.domain.Orders;
import com.example.store.domain.Products;
import com.example.store.service.OrderItemsService;
import com.example.store.service.OrdersService;
import com.example.store.service.ProductsService;
import com.example.store.util.CommandAsker;
import com.example.store.util.PasswordHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@Transactional
@SpringBootTest
public abstract class BaseExecutorTest {
    @Autowired
    protected ProductsService productsService;
    @Autowired
    protected OrdersService ordersService;
    @Autowired
    protected OrderItemsService orderItemsService;

    protected CommandAsker commandAsker;
    protected MockedStatic<PasswordHelper> passwordHelper;
    protected ByteArrayOutputStream outputStreamCaptor;
    protected Executor executor;

    protected List<Products> productsList;
    protected List<Orders> ordersList;

    protected String ls;

    @BeforeEach
    void setUp() {
        commandAsker = mock(CommandAsker.class);
        passwordHelper = mockStatic(PasswordHelper.class);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ls = System.getProperty("line.separator");

        setExecutor();
    }

    @AfterEach
    void tearDown() {
        passwordHelper.close();
        System.setOut(System.out);
    }

    protected abstract void setExecutor();

    protected void createProducts() {
        productsList = new LinkedList<>();

        Products cola = productsService.create("cola", 12);
        productsList.add(cola);

        Products orange = productsService.create("orange", 34);
        productsList.add(orange);

        Products lemonade = productsService.create("lemonade", 8);
        productsList.add(lemonade);
    }

    protected void createOrders() {
        if (productsList == null) {
            createProducts();
        }

        Products cola = productsList.get(0);
        Products orange = productsList.get(1);
        Products lemonade = productsList.get(2);

        ordersList = new LinkedList<>();

        Orders orders = ordersService.create();
        orderItemsService.create(orders, cola, 12);
        orderItemsService.create(orders, orange, 10);
        ordersList.add(orders);

        Orders orders2 = ordersService.create();
        orderItemsService.create(orders2, lemonade, 3);
        ordersList.add(orders2);

        Orders orders3 = ordersService.create();
        orderItemsService.create(orders3, lemonade, 5);
        orderItemsService.create(orders3, orange, 5);
        ordersList.add(orders3);
    }
}
