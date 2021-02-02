package com.example.store.executor.ordersItems;

import com.example.store.data.OrdersEntry;
import com.example.store.executor.Executor;
import com.example.store.service.OrdersService;
import com.example.store.util.DateHelper;
import com.example.store.util.Printer;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ShowOrdersEntryExecutor implements Executor {
    private final OrdersService ordersService;

    @Override
    public void execute() {
        List<OrdersEntry> ordersEntries = ordersService.ordersEntry();
        List<List<String>> dataList = ordersEntries.stream()
                .map(o -> List.of(
                        o.getOrderId().toString(),
                        o.getTotalPrice().toString(),
                        o.getProductName(),
                        o.getQuantity().toString(),
                        DateHelper.dateWithoutSeconds(o.getOrderCreated())
                ))
                .collect(Collectors.toList());

        Printer printer = new Printer();
        printer.addHeader(List.of("orderId", "totalPrice", "productName", "quantity", "orderCreated"));
        printer.addData(dataList);
        printer.print();
    }

}
