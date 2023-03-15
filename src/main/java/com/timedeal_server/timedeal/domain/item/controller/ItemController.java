package com.timedeal_server.timedeal.domain.item.controller;

import com.timedeal_server.timedeal.domain.item.dto.ItemReqDTO;
import com.timedeal_server.timedeal.domain.item.service.ItemService;
import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.global.api.BasicResponse;
import com.timedeal_server.timedeal.global.api.CommonResponse;
import com.timedeal_server.timedeal.global.auth.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<? extends BasicResponse> createItem(@Auth(role=Auth.Role.ADMIN) User user, @RequestBody ItemReqDTO itemReqDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>(itemService.createItem(itemReqDTO, user)));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<? extends BasicResponse> updateItem(@PathVariable Long itemId, @Auth(role=Auth.Role.ADMIN) User user, @RequestBody ItemReqDTO itemReqDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>(itemService.updateItem(itemId, itemReqDTO)));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<? extends BasicResponse> deleteItem(@PathVariable Long itemId, @Auth(role=Auth.Role.ADMIN) User user) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>(itemService.deleteItem(itemId)));
    }
}
