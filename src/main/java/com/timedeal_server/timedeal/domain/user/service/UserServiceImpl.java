package com.timedeal_server.timedeal.domain.user.service;

import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.domain.user.dto.request.UserReqDTO;
import com.timedeal_server.timedeal.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Override
    public Long join(UserReqDTO userReqDTO) {
        User user = userReqDTO.toEntity();
        userRepository.save(user);
        return user.getUserId();

    }
}
