package com.timedeal_server.timedeal.domain.user.service;

import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.domain.user.dto.request.UserLoginDTO;
import com.timedeal_server.timedeal.domain.user.dto.request.UserReqDTO;



public interface UserService {
    Long join(UserReqDTO userReqDTO);

    User login(UserLoginDTO userLoginDTO);
}
