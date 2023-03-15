package com.timedeal_server.timedeal.domain.item.service;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.dto.ItemDetailResDTO;
import com.timedeal_server.timedeal.domain.item.dto.ItemListResDTO;
import com.timedeal_server.timedeal.domain.item.dto.ItemReqDTO;
import com.timedeal_server.timedeal.domain.user.domain.User;

import java.util.List;

public interface ItemService {
    List<ItemListResDTO> getAllItems();

    ItemDetailResDTO getItem(Long itemId);

    Long createItem(ItemReqDTO itemReqDTO, User user);

    Long updateItem(Long itemId, ItemReqDTO itemReqDTO);

    Long deleteItem(Long itemId);
}
