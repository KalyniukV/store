package com.example.store.executor.products;

import com.example.store.domain.Products;
import com.example.store.executor.Executor;
import com.example.store.service.ProductsService;
import com.example.store.util.Printer;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ShowProductsInfoExecutor implements Executor {
    private final ProductsService productsService;

    @Override
    public void execute() {
        List<Products> products = productsService.getAll();
        List<List<String>> dataList = products.stream()
                .map(product -> List.of(
                        product.getName(),
                        product.getPrice().toString(),
                        product.getStatus().name()
                ))
                .collect(Collectors.toList());

        Printer printer = new Printer();
        printer.addHeader(List.of("name", "price", "status"));
        printer.addData(dataList);
        printer.print();
    }
}
