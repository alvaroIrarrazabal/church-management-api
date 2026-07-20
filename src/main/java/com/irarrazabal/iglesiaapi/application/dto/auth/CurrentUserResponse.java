package com.irarrazabal.iglesiaapi.application.dto.auth;

import com.irarrazabal.iglesiaapi.domain.model.Rol;

public record CurrentUserResponse(

        Long id,
        String username,
        String email,
        Rol rol
) {
}
