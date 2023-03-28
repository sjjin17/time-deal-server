package com.timedeal_server.timedeal.domain.item.service;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.dto.ItemDetailResDTO;
import com.timedeal_server.timedeal.domain.item.dto.ItemListResDTO;
import com.timedeal_server.timedeal.domain.item.dto.ItemReqDTO;
import com.timedeal_server.timedeal.domain.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    List<ItemListResDTO> getAllItems();

    ItemDetailResDTO getItem(Long itemId);

    Long createItem(ItemReqDTO itemReqDTO, User user, MultipartFile titleFile, List<MultipartFile> itemFiles) throws IOException;

    Long updateItem(Long itemId, ItemReqDTO itemReqDTO);

    Long deleteItem(Long itemId);

    List<ItemListResDTO> getMyItem(User user);

}
