package com.timedeal_server.timedeal.domain.item.repository;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
