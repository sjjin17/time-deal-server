package com.timedeal_server.timedeal.domain.item.service;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.domain.ItemImage;
import com.timedeal_server.timedeal.domain.item.dto.ItemDetailResDTO;
import com.timedeal_server.timedeal.domain.item.dto.ItemListResDTO;
import com.timedeal_server.timedeal.domain.item.dto.ItemReqDTO;
import com.timedeal_server.timedeal.domain.item.repository.ItemImageRepository;
import com.timedeal_server.timedeal.domain.item.repository.ItemRepository;
import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.global.exception.CustomException;
import com.timedeal_server.timedeal.global.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;

    private final S3Util s3Util;

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

    @Override
    public Long createItem(ItemReqDTO itemReqDTO, User user, MultipartFile titleFile, List<MultipartFile> itemFiles) throws IOException {
        String folderPath = UUID.randomUUID().toString();
        s3Util.createFolder(folderPath);
        String titleImage = s3Util.fileUpload(titleFile, folderPath);
        Item item = ItemReqDTO.toEntity(itemReqDTO, folderPath, titleImage, user);
        for (MultipartFile image : itemFiles) {
            String itemImageUrl = s3Util.fileUpload(image, folderPath);
            ItemImage itemImage = ItemImage.builder()
                    .imgUrl(itemImageUrl)
                    .item(item)
                    .build();
            itemImageRepository.save(itemImage);

        }

        itemRepository.save(item);
        return item.getItemId();


    }

    @Override
    public Long updateItem(Long itemId, ItemReqDTO itemReqDTO) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new CustomException("존재하지 않는 상품입니다."));
        //item.updateItem(itemReqDTO.getName(), itemReqDTO.getPrice(), itemReqDTO.getSalePrice(), itemReqDTO.getStockQuantity(), itemReqDTO.getDetail(), itemReqDTO.getStartDate(), itemReqDTO.getTitleImage());
        itemRepository.save(item);
        return item.getItemId();


    }

    @Override
    public Long deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
        return itemId;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemListResDTO> getMyItem(User user) {
        List<Item> itemList = itemRepository.findAllByUserUserId(user.getUserId());
        return itemList.stream().map(item -> ItemListResDTO.toResDTO(item)).collect(Collectors.toList());
    }
}
