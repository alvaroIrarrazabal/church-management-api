package com.irarrazabal.iglesiaapi.application.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ChangePasswordRequest(

        @NotBlank(message = "El password es obligatorio")
        String currentPassword,

        @NotBlank(message = "Debe agregar una nueba contraseña")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$",
                message = "La contraseña debe tener 8-20 caracteres, una mayúscula, una minúscula y un número"
        )
        String newPassword,

        @NotBlank(message = "Debe agregar una nueba contraseña")

        String confirmPassword
) {
}
