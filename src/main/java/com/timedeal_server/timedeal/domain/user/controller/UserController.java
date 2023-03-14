package com.timedeal_server.timedeal.domain.user.controller;

import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.domain.user.dto.request.UserLoginDTO;
import com.timedeal_server.timedeal.domain.user.dto.request.UserReqDTO;
import com.timedeal_server.timedeal.domain.user.service.UserService;
import com.timedeal_server.timedeal.global.api.BasicResponse;
import com.timedeal_server.timedeal.global.api.CommonResponse;
import com.timedeal_server.timedeal.global.auth.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/logout")
    public ResponseEntity<? extends BasicResponse> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션 제거
            session.invalidate();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>("로그아웃 성공"));
    }


    @DeleteMapping("/users")
    public ResponseEntity<? extends BasicResponse> deleteUser(@Auth User user) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponse<>(userService.deleteUser(user)));


    }

}
