package com.irarrazabal.iglesiaapi.application.dto.tithe;

import java.math.BigDecimal;

public record MemberTitheSummaryResponse(

        Long memberId,
        String memberName,
        BigDecimal totalTithes,
        BigDecimal totalAmount
) {
}
