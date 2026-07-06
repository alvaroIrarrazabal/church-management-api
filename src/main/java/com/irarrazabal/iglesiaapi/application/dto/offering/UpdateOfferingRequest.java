package com.irarrazabal.iglesiaapi.application.dto.offering;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateOfferingRequest(



        @NotNull(message = "La fecha del servicio es obligatoria")
        @PastOrPresent(message = "La fecha no puede ser futura")
        LocalDate serviceDate,

        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(
                value = "0.01",
                message = "El monto debe ser mayor que cero"
        )
        BigDecimal amount,

        @Size(
                max = 500,
                message = "La nota no puede superar los 500 caracteres"
        )
        String note,

        @NotNull(message = "Debe seleccionar un integrante")
        Long memberId
) {
}
