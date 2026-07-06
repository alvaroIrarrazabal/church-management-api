package com.irarrazabal.iglesiaapi.application.dto.members;

import com.irarrazabal.iglesiaapi.domain.model.EcclesiasticalOffice;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record UpdateMemberRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String name,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 100, message = "El apellido no puede superar los 100 caracteres")
        String lastname,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        @Size(max = 100, message = "El correo no puede superar los 150 caracteres")
        String email,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha debe ser anterior a hoy")
        LocalDate birthdate,

        @NotNull(message = "El estado es obligatorio")
        Boolean active,

        @NotNull(message = "El cargo eclesiástico es obligatorio")
        @Size(max = 100, message = "El cargo no puede superar los 50 caracteres")

        EcclesiasticalOffice ecclesiasticalOffice,

        @NotEmpty(message = "Debe seleccionar al menos un ministerio")
        List<Long> ministryIds

) {
}