package com.timedeal_server.timedeal.domain.item.domain;

import com.timedeal_server.timedeal.domain.order.domain.OrderItem;
import com.timedeal_server.timedeal.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long itemId;

    private String name;

    private int price;

    private int salePrice;

    private int stockQuantity;

    @Column(columnDefinition = "TEXT")
    private String detail;

    private LocalDateTime startDate;

    private String titleImage;

    @OneToMany(mappedBy = "item")
    private List<ItemImage> itemImageList;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seller_id")
    private User seller;

    @OneToMany(mappedBy = "item")  // item이 사라진다고 주문상품이 사라질까..? => 주문 내역은 그대로 있어야 함...
    private List<OrderItem> orderItemList;










}
