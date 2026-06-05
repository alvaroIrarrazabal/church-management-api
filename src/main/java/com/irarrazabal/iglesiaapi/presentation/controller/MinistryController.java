package com.irarrazabal.iglesiaapi.presentation.controller;

import com.irarrazabal.iglesiaapi.application.dashboard.dto.MessageResponse;
import com.irarrazabal.iglesiaapi.application.dashboard.dto.DashboardMinistryCountResponse;
import com.irarrazabal.iglesiaapi.application.member.dto.MemberResponse;
import com.irarrazabal.iglesiaapi.application.member.memberService.MemberService;
import com.irarrazabal.iglesiaapi.application.ministry.MinistryResponse;
import com.irarrazabal.iglesiaapi.application.ministry.dto.CreateMinistryRequest;
import com.irarrazabal.iglesiaapi.application.ministry.dto.UpdateMinistryRequest;
import com.irarrazabal.iglesiaapi.application.ministry.ministryService.MinistryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MinistryController {


    private final MinistryService ministryService;
    private final MemberService memberService;
    public MinistryController(MinistryService ministryService, MemberService memberService) {
        this.ministryService = ministryService;
        this.memberService = memberService;
    }

    @GetMapping("/listAllMinistries")
    @PreAuthorize("hasAnyRole('PASTOR','LIDER','ADMIN')")

    public List<MinistryResponse> listByMinistry() {

        return ministryService.findAllMinisteries();
    }


    @GetMapping("/findMinisteryById/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','LIDER','ADMIN')")
    public MinistryResponse findMinisteryById(@PathVariable Long id) {

        return ministryService.findMinistryById(id);
    }

    @PostMapping("/createMinistry")
    @PreAuthorize("hasAnyRole('PATOR','ADMIN')")
    public MinistryResponse createMinistry(@RequestBody CreateMinistryRequest request) {

        return ministryService.createMinistry(request);
    }


    @PutMapping("/updateMinistry/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public MinistryResponse updateMinistry(@PathVariable Long id , @RequestBody UpdateMinistryRequest request){

        return ministryService.updateMinistry(id,request);
    }

    @DeleteMapping("/deleteMinistry/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public ResponseEntity<MessageResponse> deleteMinistry(@PathVariable Long id){

        ministryService.deleteMinistery(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body( new MessageResponse("Eliminado correctamente"));
    }


    @GetMapping("/findByMinistry/{name}")
    @PreAuthorize("hasAnyRole('PASTOR','LIDER','ADMIN')")
    public List<MemberResponse> findByMinistry(@PathVariable String name){

        return ministryService.findByMinistry(name);
    }


    @GetMapping("/dashboardMinistryCount")
    @PreAuthorize("hasAnyRole('PASTOR','LIDER','ADMIN')")
    public List<DashboardMinistryCountResponse> dashboardMinistryCount(){

        return ministryService.dashboarMinistryCount();
    }

}
