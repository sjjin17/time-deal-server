package com.timedeal_server.timedeal.domain.user.dto.request;

import com.timedeal_server.timedeal.domain.user.domain.Role;
import com.timedeal_server.timedeal.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReqDTO {
    private String username;
    private String password;
    private String email;
    private Role role;
    private String address;


    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .address(address)
                .build();
    }
}
