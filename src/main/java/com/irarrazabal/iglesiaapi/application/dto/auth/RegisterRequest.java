package com.irarrazabal.iglesiaapi.application.dto.auth;

import com.irarrazabal.iglesiaapi.domain.model.Rol;
import jakarta.validation.constraints.*;

public record RegisterRequest(

        @NotBlank(message = "El nombre de usuario es obligatorio")
        @Size(min = 4, max = 50, message = "El nombre de usuario debe tener entre 4 y 50 caracteres")
        @Pattern(
                regexp = "^[a-zA-Z0-9._-]+$",
                message = "El nombre de usuario solo puede contener letras, números, puntos, guiones y guiones bajos"
        )
        String username,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email no es válido")
        String email,

        @NotBlank(message = "El password es obligatorio")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$",
                message = "La contraseña debe tener 8-20 caracteres, una mayúscula, una minúscula y un número"
        )
        String password,


                Rol rol

) {}
