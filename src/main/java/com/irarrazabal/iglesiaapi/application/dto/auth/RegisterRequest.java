package com.irarrazabal.iglesiaapi.application.dto.auth;

import com.irarrazabal.iglesiaapi.domain.model.Rol;

public record RegisterRequest(
        String username,
        String password,
        Rol rol
) {
}
