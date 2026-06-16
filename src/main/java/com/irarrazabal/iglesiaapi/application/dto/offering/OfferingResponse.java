package com.irarrazabal.iglesiaapi.application.dto.offering;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OfferingResponse(

        Long id,
        LocalDate serviceDate,
        BigDecimal amount,
        String note
) {
}
