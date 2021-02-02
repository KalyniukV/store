package com.example.store.executor.ordersItems;

import com.example.store.exception.ExecutorNotFoundException;
import com.example.store.executor.Executor;
import com.example.store.executor.ExecutorFactory;
import com.example.store.service.OrderItemsService;
import com.example.store.service.OrdersService;
import com.example.store.service.ProductsService;
import com.example.store.util.CommandAsker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ordersExecutorFactory")
@RequiredArgsConstructor
public class OrderItemsExecutorFactory implements ExecutorFactory {
    private final OrdersService ordersService;
    private final ProductsService productsService;
    private final OrderItemsService orderItemsService;
    private final CommandAsker commandAsker;

    @Override
    public Executor getExecutor(String command) throws ExecutorNotFoundException {
        switch (command) {
            case "create":
                return new CreateOrderItemsExecutor(
                        commandAsker,
                        orderItemsService,
                        ordersService,
                        productsService
                );
            case "update_quantity":
                return new UpdateQuantitiesExecutor(
                        commandAsker,
                        orderItemsService
                );
            case "show_orders_entry":
                return new ShowOrdersEntryExecutor(ordersService);

            case "show_orders_info":
                return new ShowOrdersInfoExecutor(ordersService);
            default:
                throw new ExecutorNotFoundException("Wrong command: \"" + command + "\" for orders\n");
        }
    }
}
