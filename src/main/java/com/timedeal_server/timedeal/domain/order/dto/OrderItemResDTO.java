package com.timedeal_server.timedeal.domain.order.dto;

import com.timedeal_server.timedeal.domain.order.domain.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemResDTO {
    private String name;
    private int price;
    private int count;

    @Builder
    public OrderItemResDTO(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public static OrderItemResDTO toDto(OrderItem orderItem) {
        return OrderItemResDTO.builder()
                .name(orderItem.getItem().getName())
                .price(orderItem.getPrice())
                .count(orderItem.getCount())
                .build();
    }
}