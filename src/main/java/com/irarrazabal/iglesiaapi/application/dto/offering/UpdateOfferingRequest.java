package com.irarrazabal.iglesiaapi.application.dto.offering;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateOfferingRequest(


        @NotNull(message = "La fecha del servicio es oblogatoria")
        LocalDate serviceDate,
        @NotNull(message = "El monto de la ofrenda es obligatoria")
        @DecimalMin(
                value = "0.01",
                message = "El monto debe ser mayor a cero"
        )
        BigDecimal amount,
        String note
) {
}
