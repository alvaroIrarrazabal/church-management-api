package com.irarrazabal.iglesiaapi.application.dto.attendance;

import com.irarrazabal.iglesiaapi.domain.model.AttendenceStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateAttendanceRequest(
        @NotNull(message="id No puede ir vacio")
        Long memberId,

        @NotNull(message="Fecha No puede ir vacio")
        LocalDate serviceDate,

        @NotNull(message=" El estado No puede ir vacio")
        AttendenceStatus status
) {
}
