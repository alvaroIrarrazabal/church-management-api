package com.irarrazabal.iglesiaapi.application.dto.auth;

import com.irarrazabal.iglesiaapi.domain.model.Rol;
import jakarta.validation.constraints.NotNull;

public record ChangeRoleRequest(

        @NotNull
        Rol rol
) {
}
