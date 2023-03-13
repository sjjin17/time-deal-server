package com.timedeal_server.timedeal.domain.item.service;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.dto.ItemListResDTO;
import com.timedeal_server.timedeal.domain.item.repository.ItemRepository;
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
}
