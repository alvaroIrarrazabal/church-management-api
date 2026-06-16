package com.irarrazabal.iglesiaapi.application.dto.tithe;

import java.math.BigDecimal;

public record TitheSummaryResponse(
        BigDecimal totalTithes,
        long totalRecords,
        long totalMembers
) {
}
