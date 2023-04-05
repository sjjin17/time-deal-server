package com.timedeal_server.timedeal.domain.order.domain;


import com.timedeal_server.timedeal.domain.item.domain.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderitem_id")
    private Long orderItemId;

    private int price;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @Builder
    public OrderItem(int price, Item item) {
        this.price = price;
        this.count = 1;
        this.item = item;
    }

    public void addOrder(Orders order) {
        this.order = order;
    }



}
