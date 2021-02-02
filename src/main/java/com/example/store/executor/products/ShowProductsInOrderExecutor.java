package com.example.store.executor.products;

import com.example.store.data.ProductsQuantity;
import com.example.store.executor.Executor;
import com.example.store.service.ProductsService;
import com.example.store.util.Printer;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ShowProductsInOrderExecutor implements Executor {
    private final ProductsService productsService;

    @Override
    public void execute() {
        List<ProductsQuantity> allProductsInOrder = productsService.getAllProductsInOrder();
        List<List<String>> dataList = allProductsInOrder.stream()
                .map(pq -> List.of(
                        pq.getName(),
                        pq.getQuantity().toString()
                ))
                .collect(Collectors.toList());

        Printer printer = new Printer();
        printer.addHeader(List.of("name", "quantity"));
        printer.addData(dataList);
        printer.print();
    }
}
