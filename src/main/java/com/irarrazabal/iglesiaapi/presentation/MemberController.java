package com.irarrazabal.iglesiaapi.presentation;

import com.irarrazabal.iglesiaapi.application.dto.dashboard.DashboardOfficeCountResponse;
import com.irarrazabal.iglesiaapi.application.dto.MessageResponse;
import com.irarrazabal.iglesiaapi.application.dto.members.CreateMemberRequest;
import com.irarrazabal.iglesiaapi.application.dto.members.MemberResponse;
import com.irarrazabal.iglesiaapi.application.dto.members.UpdateMemberRequest;
import com.irarrazabal.iglesiaapi.application.service.MemberService;
import com.irarrazabal.iglesiaapi.domain.model.EcclesiasticalOffice;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // CREATE
    @PostMapping("/members")
    @PreAuthorize("hasRole('ADMIN')")
    public MemberResponse createMember(
            @Valid @RequestBody CreateMemberRequest request) {

        return memberService.createMember(request);
    }


    // FIND BY ID
    @GetMapping("/members/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','LIDER','PASTOR')")
    public MemberResponse findMemberById(@PathVariable Long id) {

        return memberService.findMemberById(id);
    }


    // FIND ALL + PAGINATION + GENERAL SEARCH
    @GetMapping("/members")
    @PreAuthorize("hasAnyRole('ADMIN','LIDER','PASTOR')")
    public ResponseEntity<Page<MemberResponse>> findAllMembers(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "name"
            ) Pageable pageable) {

        return ResponseEntity.ok(
                memberService.findAllMembers(search, pageable)
        );
    }


    // SEARCH BY NAME
    @GetMapping("/members/search")
    @PreAuthorize("hasAnyRole('ADMIN','LIDER','PASTOR')")
    public ResponseEntity<Page<MemberResponse>> searchByName(
            @RequestParam String name,
            Pageable pageable) {

        return ResponseEntity.ok(
                memberService.searchByName(name, pageable)
        );
    }


    // FILTER BY ECCLESIASTICAL OFFICE
    @GetMapping("/members/by-office")
    @PreAuthorize("hasAnyRole('ADMIN','LIDER','PASTOR')")
    public ResponseEntity<Page<MemberResponse>> searchByEcclesiasticalOffice(
            @RequestParam EcclesiasticalOffice office,
            Pageable pageable) {

        return ResponseEntity.ok(
                memberService.searchByEcclesiasticalOffice(office, pageable)
        );
    }


    // UPDATE
    @PutMapping("/members/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MemberResponse updateMember(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMemberRequest request) {

        return memberService.updateMember(id, request);
    }


    // DELETE
    @DeleteMapping("/members/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR')")
    public MessageResponse deleteMember(@PathVariable Long id) {

        memberService.deleteMember(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new MessageResponse("Eliminado correctamente"))
                .getBody();
    }


    // LIST BY ECCLESIASTICAL OFFICE
    @GetMapping("/members/by-office/{office}")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR')")
    public List<MemberResponse> findByEcclesiasticalOffice(
            @PathVariable EcclesiasticalOffice office) {

        return memberService.findByEcclesiasticalOffice(office);
    }


    // ACTIVE MEMBERS
    @GetMapping("/members/active")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR','LIDER')")
    public List<MemberResponse> findActiveMembers() {

        return memberService.findByActiveTrue();
    }


    // INACTIVE MEMBERS
    @GetMapping("/members/inactive")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR','LIDER')")
    public List<MemberResponse> findInactiveMembers() {

        return memberService.findByInActiveFalse();
    }


    // DASHBOARD
    @GetMapping("/dashboard/members-by-office")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR','LIDER')")
    public List<DashboardOfficeCountResponse> dashboardMembersByOffice() {

        return memberService.dashboardOfficeCount();
    }
}