package com.timedeal_server.timedeal.domain.item.service;

import com.timedeal_server.timedeal.domain.item.domain.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemImageService {
    String update(Long itemId, String folderPath, MultipartFile titleFile, List<MultipartFile> images) throws IOException;
    String save(Item item, String folderPath, MultipartFile titleFile, List<MultipartFile> images) throws IOException;
}
