package com.irarrazabal.iglesiaapi.application.dto.tithe;

import java.math.BigDecimal;

public record TitheMonthlySummaryResponse(
        int year,
        int month,
        BigDecimal totalAmount,
        long totalTithes
) {
}
