package com.irarrazabal.iglesiaapi.presentation.controller;

import com.irarrazabal.iglesiaapi.application.attendance.dto.AttendenceResponse;
import com.irarrazabal.iglesiaapi.application.attendance.dto.CreateAttendenceRequest;
import com.irarrazabal.iglesiaapi.application.attendance.service.AttendenceService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/attendence")
public class AttendenceController {


    private final AttendenceService  attendenceService;
    public AttendenceController(AttendenceService attendenceSerice) {
        this.attendenceService = attendenceSerice;
    }

    @GetMapping("/findByMemberId/{memberId}")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR','LIDER')")
    public List<AttendenceResponse> findByMemberId(@PathVariable Long memberId) {

        return attendenceService.findByMember(memberId);
    }


    @PostMapping("/createAttendence")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR')")
    public AttendenceResponse createAttendence(@Valid @RequestBody CreateAttendenceRequest request){
        return attendenceService.createAttendence(request);
    }
    @GetMapping("/findByDate/{date}")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR','LIDER')")
    public List<AttendenceResponse> createfindByDateAttendence(@PathVariable LocalDate date){
        return attendenceService.findByDate(date);
    }
}
