package com.irarrazabal.iglesiaapi.application.dto.tithe;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TitheResponse(
        Long id,
        Long memberId,

        String memberName,

        BigDecimal amount,

        LocalDate date,

        String note
) {
}
