package com.example.store.executor.ordersItems;

import com.example.store.domain.OrderItems;
import com.example.store.domain.Orders;
import com.example.store.domain.Products;
import com.example.store.exception.ProductsNotFoundException;
import com.example.store.executor.Executor;
import com.example.store.form.CreateOrderForm;
import com.example.store.service.OrderItemsService;
import com.example.store.service.OrdersService;
import com.example.store.service.ProductsService;
import com.example.store.util.CommandAsker;
import com.example.store.util.Printer;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CreateOrderItemsExecutor implements Executor {
    private final CommandAsker commandAsker;
    private final OrderItemsService orderItemsService;
    private final OrdersService ordersService;
    private final ProductsService productsService;

    @Override
    public void execute() {
        printProductsId();

        CreateOrderForm createOrderForm = new CreateOrderForm(commandAsker);
        createOrderForm.fillOutTheForm();
        Map<Integer, Integer> productsMap = createOrderForm.getProductsMap();

        List<OrderItems> orderItemsList = new LinkedList<>();
        List<Integer> notFoundProductsId = new LinkedList<>();
        Orders orders = ordersService.create();
        for (Integer id : productsMap.keySet()) {
            try {
                Products product = productsService.getById(id);
                Integer quantity = productsMap.get(id);
                OrderItems orderItems = orderItemsService.create(orders, product, quantity);
                orderItemsList.add(orderItems);
            } catch (ProductsNotFoundException e) {
                notFoundProductsId.add(id);
            }
        }

        if (orderItemsList.size() > 0) {
            System.out.println("Success create order items:");
            printCreateOrderItems(orderItemsList);
        } else {
            System.out.println("Order items not created");
        }

        if (notFoundProductsId.size() > 0) {
            System.out.print("Cud not find products by id: ");
            notFoundProductsId.forEach(p -> {
                System.out.print(p + " ");
            });
        }
    }

    private void printCreateOrderItems(List<OrderItems> orderItemsList) {
        List<List<String>> dataList = orderItemsList.stream()
                .map(oi -> List.of(
                        oi.getItems().getOrderId().toString(),
                        oi.getItems().getProductId().toString(),
                        oi.getQuantity().toString()))
                .collect(Collectors.toList());

        Printer printer = new Printer();
        printer.addHeader(List.of("order id", "product id", "quantity"));
        printer.addData(dataList);
        printer.print();
    }

    private void printProductsId() {
        List<List<String>> dataList = productsService.getAll().stream()
                .map(p -> List.of(p.getId().toString(), p.getName()))
                .collect(Collectors.toList());

        Printer printer = new Printer();
        printer.addHeader(List.of("id", "name"));
        printer.addData(dataList);
        printer.print();
    }
}
