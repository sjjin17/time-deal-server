package com.timedeal_server.timedeal.domain.user.controller;

import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.domain.user.dto.request.UserLoginDTO;
import com.timedeal_server.timedeal.domain.user.dto.request.UserReqDTO;
import com.timedeal_server.timedeal.domain.user.service.UserService;
import com.timedeal_server.timedeal.global.api.BasicResponse;
import com.timedeal_server.timedeal.global.api.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입
     */
    @PostMapping("/signup")
    public ResponseEntity<? extends BasicResponse> signup(@RequestBody @Valid UserReqDTO userReqDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>(userService.join(userReqDTO)));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<? extends BasicResponse> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request){
        User user = userService.login(userLoginDTO);
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>("로그인 성공"));

    }

}
