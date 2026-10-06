package com.irarrazabal.iglesiaapi.presentation;

import com.irarrazabal.iglesiaapi.application.dto.tithe.*;
import com.irarrazabal.iglesiaapi.application.service.TitheService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tithes")
public class TitheController {

    private final TitheService titheService;

    public TitheController(TitheService titheService) {
        this.titheService = titheService;
    }


    // FIND ALL
    @GetMapping
    @PreAuthorize("hasAnyRole('PASTOR','LIDER','ADMIN')")
    public List<TitheResponse> findAllTithes() {

        return titheService.findAllTithes();
    }


    // CREATE
    @PostMapping
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public TitheResponse createTithe(
            @RequestBody @Valid CreateTitheRequest request) {

        return titheService.createTithe(request);
    }


    // FIND BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN','LIDER')")
    public TitheResponse findTitheById(@PathVariable Long id) {

        return titheService.findTitheById(id);
    }


    // FIND BY MEMBER
    @GetMapping("/member/{memberId}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN','LIDER')")
    public List<TitheResponse> findTithesByMember(
            @PathVariable Long memberId) {

        return titheService.findByMemberId(memberId);
    }


    // UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public TitheResponse updateTithe(
            @PathVariable Long id,
            @RequestBody @Valid UpdateTitheRequest request) {

        return titheService.updateTitheById(id, request);
    }


    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public void deleteTithe(@PathVariable Long id) {

        titheService.deleteTitheById(id);
    }


    // GENERAL SUMMARY
    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public TitheSummaryResponse getSummary() {

        return titheService.getSummary();
    }


    // MEMBER SUMMARY
    @GetMapping("/member/{memberId}/summary")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public MemberTitheSummaryResponse getSummaryByMember(
            @PathVariable Long memberId) {

        return titheService.getTotalByMember(memberId);
    }


    // MONTHLY SUMMARY
    @GetMapping("/summary/monthly")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public TitheMonthlySummaryResponse getMonthlySummary(
            @RequestParam int year,
            @RequestParam int month) {

        return titheService.getTotalByMonth(year, month);
    }
}