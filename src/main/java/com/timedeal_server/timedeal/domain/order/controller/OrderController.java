package com.timedeal_server.timedeal.domain.order.controller;

import com.timedeal_server.timedeal.domain.order.service.OrderService;
import com.timedeal_server.timedeal.domain.user.domain.Role;
import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.global.api.BasicResponse;
import com.timedeal_server.timedeal.global.api.CommonResponse;
import com.timedeal_server.timedeal.global.auth.Auth;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/{itemId}")
    public ResponseEntity<? extends BasicResponse> order(@Auth User user, @PathVariable Long itemId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>(orderService.order(user, itemId)));
    }
}
