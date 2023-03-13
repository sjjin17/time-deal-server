package com.timedeal_server.timedeal.domain.item.service;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.dto.ItemDetailResDTO;
import com.timedeal_server.timedeal.domain.item.dto.ItemListResDTO;
import com.timedeal_server.timedeal.domain.item.repository.ItemRepository;
import com.timedeal_server.timedeal.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ItemListResDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(item -> ItemListResDTO.toResDTO(item)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDetailResDTO getItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new CustomException("존재하지 않는 상품입니다."));
        return ItemDetailResDTO.toResDTO(item);
    }
}
