package com.irarrazabal.iglesiaapi.application.dashboard.dto;

import java.util.List;

public record DashboardResponse(

        long totalMembers,
        long activeMembers,
        long inactiveMembers,

        long totalMinistries,

        List<DashboardOfficeCountResponse> offices,

        List<DashboardMinistryCountResponse> ministries

) {
}