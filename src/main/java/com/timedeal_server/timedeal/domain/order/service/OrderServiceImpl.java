package com.timedeal_server.timedeal.domain.order.service;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.repository.ItemRepository;
import com.timedeal_server.timedeal.domain.order.domain.OrderItem;
import com.timedeal_server.timedeal.domain.order.domain.OrderStatus;
import com.timedeal_server.timedeal.domain.order.domain.Orders;
import com.timedeal_server.timedeal.domain.order.dto.OrderPageResDTO;
import com.timedeal_server.timedeal.domain.order.repository.OrderItemRepository;
import com.timedeal_server.timedeal.domain.order.repository.OrderRepository;
import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    private final OrderItemRepository orderItemRepository;

    /**
     * 단건 구매
     */
    @Override
    @Transactional
    public Long order(User user, Long itemId) {
        Item item = itemRepository.findByItemId(itemId);
        if (LocalDateTime.now().isBefore(item.getStartDate())) {
            throw new CustomException("구매 가능한 시간이 아닙니다.");
        }
        if (item.getStockQuantity() <= 0) {
            throw new CustomException("재고가 부족합니다");
        }
        List<OrderItem> orderItemList = new ArrayList<>();
        // 주문 상품 생성
        OrderItem orderItem = OrderItem.builder()
                .price(item.getSalePrice())
                .item(item)
                .build();
        item.removeStock(orderItem.getCount());

        // 주문 생성
        Orders order = Orders.builder()
                .orderDate(LocalDateTime.now())
                .user(user)
                .status(OrderStatus.ORDER)
                .orderItemList(orderItemList)
                .build();
        orderRepository.save(order);
        orderItem.addOrder(orderRepository.findById(order.getOrderId()).orElseThrow(() -> new CustomException("존재하지 않는 상품입니다.")));
        orderItemRepository.save(orderItem);
        return order.getOrderId();
    }



    @Override
    @Transactional(readOnly = true)
    public OrderPageResDTO getMyOrder(User user, Pageable pageable) {
        Page<Orders> orderList = orderRepository.findAllByUserId(user.getUserId(), pageable);
        return OrderPageResDTO.toDto(orderList);

    }
}
