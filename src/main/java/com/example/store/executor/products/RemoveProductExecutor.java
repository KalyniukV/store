package com.example.store.executor.products;

import com.example.store.domain.Products;
import com.example.store.exception.CancelException;
import com.example.store.exception.PasswordException;
import com.example.store.exception.ProductsNotFoundException;
import com.example.store.executor.Executor;
import com.example.store.form.RemoveProductForm;
import com.example.store.service.ProductsService;
import com.example.store.util.CommandAsker;
import com.example.store.util.Printer;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RemoveProductExecutor implements Executor {
    private final ProductsService productsService;
    private final CommandAsker commandAsker;

    @Override
    public void execute() {
        showProducts();
        RemoveProductForm removeProductForm = new RemoveProductForm(commandAsker);
        try {
            removeProductForm.fillOutTheForm();
            if ("all".equals(removeProductForm.getId())) {
                removeAllProducts();
            } else {
                removeProductById(Integer.valueOf(removeProductForm.getId()));
            }
            System.out.println("Remove success");
        } catch (ProductsNotFoundException |
                PasswordException |
                CancelException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeProductById(Integer id) throws ProductsNotFoundException {
        productsService.remove(id);
    }

    private void removeAllProducts() throws ProductsNotFoundException {
        List<Products> products = productsService.getAll();

        for (Products product : products) {
            productsService.remove(product.getId());
        }
    }

    private void showProducts() {
        List<Products> products = productsService.getAll();
        List<List<String>> dataList = products.stream()
                .map(product -> List.of(
                        product.getId().toString(),
                        product.getName()
                ))
                .collect(Collectors.toList());

        Printer printer = new Printer();
        printer.addHeader(List.of("id", "name"));
        printer.addData(dataList);
        printer.print();
    }
}
