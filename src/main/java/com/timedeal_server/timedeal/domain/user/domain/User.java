package com.timedeal_server.timedeal.domain.user.domain;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.order.domain.Orders;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;


    private String username;

    private String password;

    private String email;

    private Role role;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Item> itemList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Orders> ordersList;

    @Builder
    public User(String username, String password, String email, Role role, Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.address = address;
    }

}
