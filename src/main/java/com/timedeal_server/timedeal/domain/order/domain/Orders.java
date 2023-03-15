package com.timedeal_server.timedeal.domain.order.domain;

import com.timedeal_server.timedeal.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long orderId;

    private LocalDateTime orderDate;

    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyer_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;


    @Builder
    public Orders(LocalDateTime orderDate, OrderStatus status, User user, List<OrderItem> orderItemList) {
        this.orderDate = orderDate;
        this.status = status;
        this.user = user;
        this.orderItemList = orderItemList;
    }

}
