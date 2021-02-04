package com.example.store.service;

import com.example.store.data.OrdersEntry;
import com.example.store.domain.Orders;
import com.example.store.exception.OrdersNotFoundException;
import com.example.store.repository.OrdersRepository;
import com.example.store.util.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;


    @Override
    public Orders create() {
        Orders orders = Orders.builder()
                                .userId(UserHelper.getUserId())
                                .status("created")
                                .build();

        Orders saveOrders = ordersRepository.save(orders);

        return saveOrders;
    }

    @Override
    public void remove(Orders orders) {
        ordersRepository.delete(orders);
    }

    @Override
    public Orders getById(Integer id) throws OrdersNotFoundException {
        Optional<Orders> ordersOptional = ordersRepository.findById(id);

        if (ordersOptional.isPresent()) {
            return ordersOptional.get();
        }

        throw new OrdersNotFoundException(id);
    }

    @Override
    public List<Orders> getAll() {
        return ordersRepository.findAll();
    }

    @Override
    public List<OrdersEntry> ordersEntry() {
        List<Object[]> orders = ordersRepository.ordersEntry();

        return orders.stream()
                .map(o ->
                        new OrdersEntry(
                                Integer.valueOf(o[0].toString()),
                                Integer.valueOf(o[1].toString()),
                                (String) o[2],
                                Integer.valueOf(o[3].toString()),
                                o[4].toString()
                               )
                )
                .collect(Collectors.toList());
    }
}
