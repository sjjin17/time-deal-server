package com.timedeal_server.timedeal.domain.order.dto;

import com.timedeal_server.timedeal.domain.order.domain.Orders;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderPageResDTO {
    private int currentPage;
    private int totalPage;
    private List<OrderResDTO> orderList;


    @Builder
    public OrderPageResDTO(int currentPage, int totalPage, List<OrderResDTO> orderList) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.orderList = orderList;
    }

    public static OrderPageResDTO toDto(Page<Orders> orders) {
        return OrderPageResDTO.builder()
                .currentPage(orders.getNumber())
                .totalPage(orders.getTotalPages())
                .orderList(orders.stream().map(order -> OrderResDTO.toDto(order, order.getOrderItemList().stream().map(orderItem -> OrderItemResDTO.toDto(orderItem)).collect(Collectors.toList()))).collect(Collectors.toList()))
                .build();
    }
}
