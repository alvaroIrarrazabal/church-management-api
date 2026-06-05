package com.irarrazabal.iglesiaapi.application.auth.dto;

import com.irarrazabal.iglesiaapi.domain.Enum.Rol;

public record RegisterRequest(
        String username,
        String password,
        Rol rol
) {
}
