package com.timedeal_server.timedeal.domain.order.service;

import com.timedeal_server.timedeal.domain.order.dto.OrderResDTO;
import com.timedeal_server.timedeal.domain.user.domain.User;

import java.util.List;

public interface OrderService {
    Long order(User user, Long itemId);

    List<OrderResDTO> getMyOrder(User user);
}
