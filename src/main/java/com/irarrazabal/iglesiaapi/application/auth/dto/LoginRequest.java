package com.irarrazabal.iglesiaapi.application.auth.dto;

public record LoginRequest(

        String username,
        String password
) {
}
