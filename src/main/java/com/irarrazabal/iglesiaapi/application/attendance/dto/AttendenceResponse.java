package com.irarrazabal.iglesiaapi.application.attendance.dto;

import com.irarrazabal.iglesiaapi.domain.Enum.AttendenceStatus;

import java.time.LocalDate;

public record AttendenceResponse(

        Long id,
        String memberName,
        LocalDate serviceDate,
        AttendenceStatus status
) {
}
