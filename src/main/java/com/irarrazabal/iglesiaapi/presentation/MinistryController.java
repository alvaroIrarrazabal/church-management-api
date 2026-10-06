package com.irarrazabal.iglesiaapi.presentation;

import com.irarrazabal.iglesiaapi.application.dto.MessageResponse;
import com.irarrazabal.iglesiaapi.application.dto.dashboard.DashboardMinistryCountResponse;
import com.irarrazabal.iglesiaapi.application.dto.members.MemberResponse;
import com.irarrazabal.iglesiaapi.application.dto.ministry.*;
import com.irarrazabal.iglesiaapi.application.service.MinistryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MinistryController {

    private final MinistryService ministryService;

    public MinistryController(MinistryService ministryService) {
        this.ministryService = ministryService;
    }


    // FIND ALL
    @GetMapping("/ministries")
    @PreAuthorize("hasAnyRole('PASTOR','LIDER','ADMIN')")
    public List<MinistryResponse> findAllMinistries() {

        return ministryService.findAllMinisteries();
    }


    // FIND BY ID
    @GetMapping("/ministries/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','LIDER','ADMIN')")
    public MinistryResponse findMinistryById(@PathVariable Long id) {

        return ministryService.findMinistryById(id);
    }


    // CREATE
    @PostMapping("/ministries")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public MinistryResponse createMinistry(
            @RequestBody CreateMinistryRequest request) {

        return ministryService.createMinistry(request);
    }


    // UPDATE
    @PutMapping("/ministries/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public MinistryResponse updateMinistry(
            @PathVariable Long id,
            @RequestBody UpdateMinistryRequest request) {

        return ministryService.updateMinistry(id, request);
    }


    // DELETE
    @DeleteMapping("/ministries/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public ResponseEntity<MessageResponse> deleteMinistry(
            @PathVariable Long id) {

        ministryService.deleteMinistery(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new MessageResponse("Eliminado correctamente"));
    }


    // MEMBERS BY MINISTRY
    @GetMapping("/ministries/{name}/members")
    @PreAuthorize("hasAnyRole('PASTOR','LIDER','ADMIN')")
    public List<MemberResponse> findMembersByMinistry(
            @PathVariable String name) {

        return ministryService.findByMinistry(name);
    }


    // DASHBOARD
    @GetMapping("/dashboard/members-by-ministry")
    @PreAuthorize("hasAnyRole('PASTOR','LIDER','ADMIN')")
    public List<DashboardMinistryCountResponse> dashboardMembersByMinistry() {

        return ministryService.dashboarMinistryCount();
    }
}