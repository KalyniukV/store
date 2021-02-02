package com.example.store.executor.products;

import com.example.store.executor.Executor;
import com.example.store.form.CreateProductForm;
import com.example.store.service.ProductsService;
import com.example.store.util.CommandAsker;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProductExecutor implements Executor {
    private final ProductsService productsService;
    private final CommandAsker commandAsker;

    @Override
    public void execute() {
        CreateProductForm createProductForm = new CreateProductForm(commandAsker);
        createProductForm.fillOutTheForm();

        productsService.create(createProductForm.getName(), createProductForm.getPrice());
    }
}
