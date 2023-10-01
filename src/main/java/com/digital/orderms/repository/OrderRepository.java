package com.digital.orderms.repository;

import com.digital.orderms.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}