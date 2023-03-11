package com.timedeal_server.timedeal.domain.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginDTO {
    private String username;
    private String password;

    @Builder
    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
