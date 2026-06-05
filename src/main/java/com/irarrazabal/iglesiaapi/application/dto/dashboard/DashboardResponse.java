package com.irarrazabal.iglesiaapi.application.dto.dashboard;

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