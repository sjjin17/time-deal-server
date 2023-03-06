package com.timedeal_server.timedeal.domain.user.domain;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.order.domain.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    private String username;

    private String password;

    private String email;

    private Role role;

    private String address;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Item> itemList;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Orders> ordersList;



}
