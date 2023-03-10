package com.timedeal_server.timedeal.global.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse extends BasicResponse{
    private boolean success;
    private String message;

    public ErrorResponse(String message) {
        success = false;
        this.message = message;
    }



}
