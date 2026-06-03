package com.irarrazabal.iglesiaapi.application.dto.auth;

public record LoginRequest(

        String username,
        String password
) {
}
