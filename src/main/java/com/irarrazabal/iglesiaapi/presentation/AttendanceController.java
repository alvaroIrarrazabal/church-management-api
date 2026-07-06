package com.irarrazabal.iglesiaapi.presentation;

import com.irarrazabal.iglesiaapi.application.dto.attendance.AttendanceResponse;
import com.irarrazabal.iglesiaapi.application.dto.attendance.CreateAttendanceRequest;
import com.irarrazabal.iglesiaapi.application.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/attendence")
public class AttendanceController {

    private final AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendenceSerice) {
        this.attendanceService = attendenceSerice;
    }

    @GetMapping("/findByMemberId/{memberId}")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR','LIDER')")
    public List<AttendanceResponse> findByMemberId(@PathVariable Long memberId) {

        return attendanceService.findByMember(memberId);
    }


    @PostMapping("/createAttendence")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR')")
    public AttendanceResponse createAttendence(@Valid @RequestBody CreateAttendanceRequest request){
        return attendanceService.createAttendence(request);
    }
    @GetMapping("/findByDate/{date}")
    @PreAuthorize("hasAnyRole('ADMIN','PASTOR','LIDER')")
    public List<AttendanceResponse> createfindByDateAttendence(@PathVariable LocalDate date){
        return attendanceService.findByDate(date);
    }
}
