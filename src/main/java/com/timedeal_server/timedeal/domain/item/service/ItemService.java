package com.timedeal_server.timedeal.domain.item.service;

import com.timedeal_server.timedeal.domain.item.dto.ItemListResDTO;

import java.util.List;

public interface ItemService {
    List<ItemListResDTO> getAllItems();
}
