package com.timedeal_server.timedeal.domain.order.repository;

import com.timedeal_server.timedeal.domain.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("select o from Orders o join fetch o.orderItemList oi join fetch oi.item where o.user.userId = :userId")
    List<Orders> findAllByUserId(@Param("userId") Long userId);
}
