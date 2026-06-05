package com.irarrazabal.iglesiaapi.application.dto.ministry;

import com.irarrazabal.iglesiaapi.domain.model.EcclesiasticalOffice;

import java.util.List;

public record ByMinistryResponse(

        Long id,
        String name,
        String lastname,
        String email,
        boolean asset


) {
}
