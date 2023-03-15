package com.timedeal_server.timedeal.domain.order.repository;

import com.timedeal_server.timedeal.domain.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
