package com.irarrazabal.iglesiaapi.application.dto.integrante;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record UpdateIntegranteRequest(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "El apellido es obligatorio ")
        String apellido,
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato valido")
        String correo,
        @NotNull(message = "La fecha de necimiento es obligatoria")
        @Past(message = "La fecha debe ser anterior hoy")
        LocalDate fecaNacimiento,
        @NotNull(message = "El estado es obligatorio")
        Boolean activo,
        @NotEmpty(message = "Debe seleccionar almenos un ministerio")
        List<Long> ministeriosIds


) {
}
