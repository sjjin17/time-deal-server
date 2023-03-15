package com.timedeal_server.timedeal.domain.user.domain;


import com.timedeal_server.timedeal.global.auth.Auth;

public enum Role {
    USER, ADMIN;
    public static Auth.Role User;
}
