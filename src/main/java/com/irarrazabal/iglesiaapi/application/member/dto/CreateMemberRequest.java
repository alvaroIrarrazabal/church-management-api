package com.irarrazabal.iglesiaapi.application.member.dto;

import com.irarrazabal.iglesiaapi.domain.Enum.EcclesiasticalOffice;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record CreateMemberRequest(

        @NotBlank(message = "El nombre es obligatorio")
        String name,
        @NotBlank(message = "El apellido es obligatorio ")
        String lastname,
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato valido")
        String email,
        @NotNull(message = "La fecha de necimiento es obligatoria")
        @Past(message = "La fecha debe ser anterior hoy")
        LocalDate birthdate ,
        @NotNull(message = "El estado es obligatorio")
        Boolean asset,
        @NotNull(message = "El cargo es obligatorio")
        EcclesiasticalOffice ecclesiasticalOffice,
        @NotEmpty(message = "Debe seleccionar almenos un ministerio")
        List<Long> ministryId





) {
}
