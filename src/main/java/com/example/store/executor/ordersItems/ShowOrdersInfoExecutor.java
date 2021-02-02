package com.example.store.executor.ordersItems;

import com.example.store.domain.Orders;
import com.example.store.executor.Executor;
import com.example.store.service.OrdersService;
import com.example.store.util.DateHelper;
import com.example.store.util.Printer;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ShowOrdersInfoExecutor implements Executor {
    private final OrdersService ordersService;

    @Override
    public void execute() {
        List<Orders> ordersList = ordersService.getAll();
        List<List<String>> dataList = ordersList.stream()
                .map(o -> List.of(
                        o.getId().toString(),
                        o.getUserId().toString(),
                        o.getStatus(),
                        DateHelper.dateWithoutSeconds(o.getCreatedAt())
                ))
                .collect(Collectors.toList());

        Printer printer = new Printer();
        printer.addHeader(List.of("id", "userId", "status", "createdAt"));
        printer.addData(dataList);
        printer.print();
    }
}
