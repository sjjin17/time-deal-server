package com.timedeal_server.timedeal.domain.item.repository;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Lock(value= LockModeType.PESSIMISTIC_READ)
    Item findByItemId(Long itemId);

}
