package com.irarrazabal.iglesiaapi.application.attendance.dto;

import com.irarrazabal.iglesiaapi.domain.Enum.AttendenceStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateAttendenceRequest(

        @NotNull(message="id No puede ir vacio")
        Long memberId,

        @NotNull(message="Fecha No puede ir vacio")
        LocalDate serviceDate,

        @NotNull(message=" El estado No puede ir vacio")
        AttendenceStatus status


) {
}
