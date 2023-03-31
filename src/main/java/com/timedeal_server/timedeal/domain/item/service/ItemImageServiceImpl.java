package com.timedeal_server.timedeal.domain.item.service;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import com.timedeal_server.timedeal.domain.item.domain.ItemImage;
import com.timedeal_server.timedeal.domain.item.repository.ItemImageRepository;
import com.timedeal_server.timedeal.domain.item.repository.ItemRepository;
import com.timedeal_server.timedeal.global.exception.CustomException;
import com.timedeal_server.timedeal.global.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImageServiceImpl implements ItemImageService {

    private final S3Util s3Util;
    private final ItemImageRepository itemImageRepository;
    private final ItemRepository itemRepository;

    @Override
    public String update(Long itemId, String folderPath, MultipartFile titleFile, List<MultipartFile> images) throws IOException {
        // 폴더의 모든 이미지 삭제 후 다시 저장
        System.out.println(folderPath);
        List<String> urls = itemImageRepository.findByItemItemId(itemId);
        urls.add(itemRepository.findById(itemId).orElseThrow(() -> new CustomException("존재하지 않는 상품입니다.")).getTitleImage());
        urls.stream().forEach(url -> s3Util.deleteFile(url, folderPath));
        itemImageRepository.deleteAllByItemItemId(itemId);
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new CustomException("존재하지 않는 상품 번호입니다."));
        String titleImage = save(item, folderPath, titleFile, images);
        item.setTitleImage(titleImage);
        itemRepository.save(item);
        return titleImage;

    }

    public String save(Item item, String folderPath, MultipartFile titleFile, List<MultipartFile> images) throws IOException {
        int i = 1;
        String titleImage = s3Util.fileUpload(titleFile, folderPath, "item" + i);
        i += 1;
        for (MultipartFile image: images) {
            String itemImageUrl = s3Util.fileUpload(image, folderPath, "item" + i);
            ItemImage itemImage = ItemImage.builder()
                    .imgUrl(itemImageUrl)
                    .item(item)
                    .build();
            itemImageRepository.save(itemImage);
            System.out.println("저장!");
            i += 1;
        }
        return titleImage;
    }
}
