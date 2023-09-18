package com.digital.orderms.application;

import com.digital.orderms.domain.Order;
import com.digital.orderms.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order create(Order order) {
        return orderRepository.save(order);
    }
}