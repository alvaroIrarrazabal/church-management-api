package com.irarrazabal.iglesiaapi.application.dto.attendance;

import com.irarrazabal.iglesiaapi.domain.model.AttendenceStatus;

import java.time.LocalDate;

public record AttendanceResponse(

        Long id,
        String memberName,
        LocalDate serviceDate,
        AttendenceStatus status
) {
}
