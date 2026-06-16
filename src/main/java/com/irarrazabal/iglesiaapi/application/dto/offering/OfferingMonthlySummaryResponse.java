package com.irarrazabal.iglesiaapi.application.dto.offering;

import java.math.BigDecimal;

public record OfferingMonthlySummaryResponse(
        int year,
        int month,
        BigDecimal totalAmount,
        long totalRecords
) {
}
