package com.timedeal_server.timedeal.domain.user.service;

import com.timedeal_server.timedeal.domain.user.domain.Address;
import com.timedeal_server.timedeal.domain.user.domain.Role;
import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.domain.user.dto.request.UserLoginDTO;
import com.timedeal_server.timedeal.domain.user.dto.request.UserReqDTO;
import com.timedeal_server.timedeal.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        UserReqDTO userReqDTO = UserReqDTO.builder()
                .username("test")
                .password("hellotest111*")
                .role(Role.USER)
                .email("test@naver.com")
                .build();
        userService.join(userReqDTO);

    }

    @AfterEach
    public void afterEach() {
        userRepository.deleteAll();
    }


    @Test
    public void 로그인성공() {
        // given
        String username = "test";
        String password = "hellotest111*";
        UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                .username(username)
                .password(password)
                .build();
        // when
        User user = userService.login(userLoginDTO);
        // then
        Assertions.assertThat(username).isEqualTo(user.getUsername());
        Assertions.assertThat(password).isEqualTo(user.getPassword());

    }


}
