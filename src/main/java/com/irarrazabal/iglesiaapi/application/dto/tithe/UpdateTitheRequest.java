package com.irarrazabal.iglesiaapi.application.dto.tithe;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateTitheRequest(


        @NotNull(message = "El integrante es obligatorio")
        Long memberId,

        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser mayor que cero")
        BigDecimal amount,

        @NotNull(message = "La fecha es obligatoria")
        LocalDate date,

        String note
) {
}
