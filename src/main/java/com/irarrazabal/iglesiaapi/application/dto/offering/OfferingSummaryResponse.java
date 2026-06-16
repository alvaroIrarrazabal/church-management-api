package com.irarrazabal.iglesiaapi.application.dto.offering;

import java.math.BigDecimal;

public record OfferingSummaryResponse(

        BigDecimal totalAmount,
        long totalRecords
) {
}
