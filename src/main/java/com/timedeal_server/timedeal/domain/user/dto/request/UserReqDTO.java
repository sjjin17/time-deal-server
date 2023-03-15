package com.timedeal_server.timedeal.domain.user.dto.request;

import com.timedeal_server.timedeal.domain.user.domain.Address;
import com.timedeal_server.timedeal.domain.user.domain.Role;
import com.timedeal_server.timedeal.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserReqDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;
    @Email
    private String email;
    private Role role;
    private Address address;


    @Builder
    public UserReqDTO(String username, String password, String email, Role role, Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.address = address;
    }
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
