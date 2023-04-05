package com.timedeal_server.timedeal.domain.order.service;

import com.timedeal_server.timedeal.domain.order.dto.OrderPageResDTO;
import com.timedeal_server.timedeal.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Long order(User user, Long itemId);

    OrderPageResDTO getMyOrder(User user, Pageable pageable);
}
