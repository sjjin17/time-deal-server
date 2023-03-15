package com.timedeal_server.timedeal.domain.order.dto;

import com.timedeal_server.timedeal.domain.order.domain.OrderStatus;
import com.timedeal_server.timedeal.domain.order.domain.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResDTO {
    private Long orderId;
    private List<OrderItemResDTO> orderItemResDTOList;
    private String date;
    private String status;

    @Builder
    public OrderResDTO(Long orderId, List<OrderItemResDTO> orderItemResDTOList, String date, String status) {
        this.orderId = orderId;
        this.orderItemResDTOList = orderItemResDTOList;
        this.date = date;
        this.status = status;

    }


    public static OrderResDTO toDto(Orders order, List<OrderItemResDTO> orderItemResDTOList) {
        return OrderResDTO.builder()
                .orderId(order.getOrderId())
                .orderItemResDTOList(orderItemResDTOList)
                .date(order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status(order.getStatus().toString())
                .build();
    }
}
