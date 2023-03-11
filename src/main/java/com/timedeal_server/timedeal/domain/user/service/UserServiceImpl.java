package com.timedeal_server.timedeal.domain.user.service;

import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.domain.user.dto.request.UserLoginDTO;
import com.timedeal_server.timedeal.domain.user.dto.request.UserReqDTO;
import com.timedeal_server.timedeal.domain.user.repository.UserRepository;
import com.timedeal_server.timedeal.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Override
    public Long join(UserReqDTO userReqDTO) {
        // 중복회원 검증
        if (userRepository.existsByUsername(userReqDTO.getUsername())) {
            throw new CustomException("이미 존재하는 회원입니다.");
        }
        User user = userReqDTO.toEntity();
        userRepository.save(user);
        return user.getUserId();

    }

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        // 회원이 맞는지 확인
        return userRepository.findByUsername(userLoginDTO.getUsername())
                .filter(u -> u.getPassword().equals(userLoginDTO.getPassword()))
                .orElseThrow(() -> new CustomException("아이디 또는 비밀번호가 일치하지 않습니다."));


    }
}
