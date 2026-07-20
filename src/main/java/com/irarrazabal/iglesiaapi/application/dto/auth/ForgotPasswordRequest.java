package com.irarrazabal.iglesiaapi.application.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequest(

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe ingresar un email válido")
        String email
) {
}
