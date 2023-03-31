package com.timedeal_server.timedeal.domain.item.controller;

import com.timedeal_server.timedeal.domain.item.service.ItemImageService;
import com.timedeal_server.timedeal.global.api.BasicResponse;
import com.timedeal_server.timedeal.global.api.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ItemImageController {

    private final ItemImageService itemImageService;

    @PutMapping("{itemId}/{folderPath}")
    public ResponseEntity<? extends BasicResponse> updateImage(@PathVariable Long itemId, @PathVariable String folderPath, @RequestPart(value="title") MultipartFile titleFile, @RequestPart(value="images") List<MultipartFile> images) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>(itemImageService.update(itemId, folderPath, titleFile,images)));
    }
}
