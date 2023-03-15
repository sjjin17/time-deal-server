package com.timedeal_server.timedeal.domain.order.service;

import com.timedeal_server.timedeal.domain.user.domain.User;

public interface OrderService {
    Long order(User user, Long itemId);
}
