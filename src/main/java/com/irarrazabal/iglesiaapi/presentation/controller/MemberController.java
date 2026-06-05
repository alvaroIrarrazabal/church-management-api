package com.irarrazabal.iglesiaapi.presentation.controller;

import com.irarrazabal.iglesiaapi.application.dashboard.dto.DashboardOfficeCountResponse;
import com.irarrazabal.iglesiaapi.application.dashboard.dto.MessageResponse;
import com.irarrazabal.iglesiaapi.application.member.dto.CreateMemberRequest;
import com.irarrazabal.iglesiaapi.application.member.dto.MemberResponse;
import com.irarrazabal.iglesiaapi.application.member.dto.UpdateMemberRequest;
import com.irarrazabal.iglesiaapi.application.member.memberService.MemberService;
import com.irarrazabal.iglesiaapi.domain.Enum.EcclesiasticalOffice;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class MemberController {


    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping("/createMember")
    @PreAuthorize("hasRole('ADMIN')")
    public MemberResponse createMember(@Valid @RequestBody CreateMemberRequest request) {

        return memberService.createMember(request);

    }


    @GetMapping("/findMemberById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','LIDER')")
    public MemberResponse findMemberById(@PathVariable Long id){
        return memberService.findMemberById(id);
    }

    @GetMapping("/findAllMember")
    @PreAuthorize("hasAnyRole('ADMIN','LIDER')")
    public List<MemberResponse> findAllMember(){
        return  memberService.findAllMember();
    }


    @PutMapping("/updateMember/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MemberResponse updateMember(@PathVariable Long id, @Valid @RequestBody UpdateMemberRequest request){

        return memberService.updateMember(id, request);
    }

    @DeleteMapping("deleteMember/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR')")
    public MessageResponse deleteMember(@PathVariable Long id){

         memberService.deleteMember(id);

         return ResponseEntity.status(HttpStatus.OK)
                 .body( new MessageResponse("Eliminado correctamente")).getBody();
    }


    @GetMapping("findByEcclesiasticalOffice/{ecclesiasticalOffice}")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR')")
    public List<MemberResponse> findByEcclesiasticalOffice(@PathVariable EcclesiasticalOffice ecclesiasticalOffice){

        return memberService.findByEcclesiasticalOffice(ecclesiasticalOffice);

    }
    @GetMapping("/findByActiveTrue")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR','LIDER')")
    public List<MemberResponse> findBYActiveTrue( ){

        return memberService.findByActiveTrue();

    }



    @GetMapping("/findByInActiveFalse")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR','LIDER')")
    public List<MemberResponse> findBYInactiveFalse( ){

        return memberService.findByInActiveFalse();

    }

    @GetMapping("/dashboardOfficeCount")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR','LIDER')")
    public List<DashboardOfficeCountResponse> dashboarOfficeCount( ){

        return memberService.dashboardOfficeCount();

    }






}
