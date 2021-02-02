package com.example.store.executor.products;

import com.example.store.exception.ExecutorNotFoundException;
import com.example.store.executor.Executor;
import com.example.store.executor.ExecutorFactory;
import com.example.store.service.ProductsService;
import com.example.store.util.CommandAsker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("productsExecutorFactory")
public class ProductsExecutorFactory implements ExecutorFactory {
    private final ProductsService productsService;
    private final CommandAsker commandAsker;

    @Override
    public Executor getExecutor(String command) throws ExecutorNotFoundException {
        switch (command) {
            case "create":
               return new CreateProductExecutor(
                        productsService,
                        commandAsker
                );
            case "remove":
                return new RemoveProductExecutor(
                        productsService,
                        commandAsker
                );
            case "show_all":
                return new ShowProductsInfoExecutor(productsService);
            case "show_in_order":
                return  new ShowProductsInOrderExecutor(productsService);
            default:
                throw new ExecutorNotFoundException("Wrong command: \"" + command + "\" for products\n");
        }
    }
}
