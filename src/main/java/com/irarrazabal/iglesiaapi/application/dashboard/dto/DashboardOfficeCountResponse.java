package com.irarrazabal.iglesiaapi.application.dashboard.dto;

import com.irarrazabal.iglesiaapi.domain.Enum.EcclesiasticalOffice;

public record DashboardOfficeCountResponse(

EcclesiasticalOffice ecclesiasticalOffice,
Long total


) {
}
