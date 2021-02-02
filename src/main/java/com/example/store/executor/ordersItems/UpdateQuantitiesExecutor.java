package com.example.store.executor.ordersItems;

import com.example.store.executor.Executor;
import com.example.store.form.UpdateQuantitiesForm;
import com.example.store.service.OrderItemsService;
import com.example.store.util.CommandAsker;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateQuantitiesExecutor implements Executor {
    private final CommandAsker commandAsker;
    private final OrderItemsService orderItemsService;

    @Override
    public void execute() {
        UpdateQuantitiesForm form = new UpdateQuantitiesForm(commandAsker);
        form.fillOutTheForm();

        orderItemsService.updateQuantity(form.getOrderId(), form.getProductId(), form.getQuantity());
    }
}
