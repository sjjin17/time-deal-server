package com.timedeal_server.timedeal.domain.order.dto;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.dto.ItemResDTO;
import com.timedeal_server.timedeal.domain.order.domain.OrderItem;
import com.timedeal_server.timedeal.domain.order.repository.OrderItemRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderItemResDTO {
    private ItemResDTO itemResDTO;
    private int price;
    private int count;

    @Builder
    public OrderItemResDTO(ItemResDTO itemResDTO, int price, int count) {
        this.itemResDTO = itemResDTO;
        this.price = price;
        this.count = count;
    }

    public static OrderItemResDTO toDto(OrderItem orderItem) {
        return OrderItemResDTO.builder()
                .itemResDTO(ItemResDTO.toDto(orderItem.getItem()))
                .price(orderItem.getPrice())
                .count(orderItem.getCount())
                .build();
    }
}
