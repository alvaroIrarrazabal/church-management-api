package com.irarrazabal.iglesiaapi.presentation;

import com.irarrazabal.iglesiaapi.application.dto.dashboard.DashboardResponse;
import com.irarrazabal.iglesiaapi.application.service.DashboardService;
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
