package com.irarrazabal.iglesiaapi.application.dto.dashboard;

import com.irarrazabal.iglesiaapi.domain.model.EcclesiasticalOffice;

public record DashboardOfficeCountResponse(

EcclesiasticalOffice ecclesiasticalOffice,
Long total


) {
}
