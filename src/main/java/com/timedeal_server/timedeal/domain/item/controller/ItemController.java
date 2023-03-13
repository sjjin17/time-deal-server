package com.timedeal_server.timedeal.domain.item.controller;

import com.timedeal_server.timedeal.domain.item.service.ItemService;
import com.timedeal_server.timedeal.global.api.BasicResponse;
import com.timedeal_server.timedeal.global.api.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<? extends BasicResponse> getAllItems() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>(itemService.getAllItems()));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<? extends BasicResponse> getItem(@PathVariable Long itemId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>(itemService.getItem(itemId)));
    }
}
