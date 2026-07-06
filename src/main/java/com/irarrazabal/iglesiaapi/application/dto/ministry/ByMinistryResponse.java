package com.irarrazabal.iglesiaapi.application.dto.ministry;

public record ByMinistryResponse(

        Long id,
        String name,
        String lastname,
        String email,
        boolean active


) {
}
