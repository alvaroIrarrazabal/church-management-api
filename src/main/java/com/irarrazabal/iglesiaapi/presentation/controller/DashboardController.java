package com.irarrazabal.iglesiaapi.presentation.controller;

import com.irarrazabal.iglesiaapi.application.dashboard.dto.DashboardResponse;
import com.irarrazabal.iglesiaapi.application.dashboard.dashboardService.DashboardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DashboardController {


    private final DashboardService dashboardService;

    public DashboardController( DashboardService dashboardService ) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN','LIDER')")
    public DashboardResponse getDashboard( )
    {
        return dashboardService.getDashboard();
    }

}
