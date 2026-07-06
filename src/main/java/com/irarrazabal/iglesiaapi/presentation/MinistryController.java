package com.irarrazabal.iglesiaapi.presentation;

import com.irarrazabal.iglesiaapi.application.dto.MessageResponse;
import com.irarrazabal.iglesiaapi.application.dto.dashboard.DashboardMinistryCountResponse;
import com.irarrazabal.iglesiaapi.application.dto.members.MemberResponse;
import com.irarrazabal.iglesiaapi.application.dto.ministry.*;
import com.irarrazabal.iglesiaapi.application.service.MemberService;
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
