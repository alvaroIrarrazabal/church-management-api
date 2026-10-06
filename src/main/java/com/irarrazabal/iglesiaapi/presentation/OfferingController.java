package com.irarrazabal.iglesiaapi.presentation;

import com.irarrazabal.iglesiaapi.application.dto.offering.*;
import com.irarrazabal.iglesiaapi.application.service.OfferingService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offerings")
public class OfferingController {

    private final OfferingService offeringService;

    public OfferingController(OfferingService offeringService) {
        this.offeringService = offeringService;
    }

    // FIND ALL
    @GetMapping
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN','LIDER')")
    public List<OfferingResponse> findAllOfferings() {
        return offeringService.findAllOfferings();
    }

    // FIND BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN','LIDER')")
    public OfferingResponse findOfferingById(@PathVariable Long id) {
        return offeringService.findById(id);
    }

    // CREATE
    @PostMapping
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public OfferingResponse createOffering(
            @RequestBody @Valid CreateOfferingRequest request) {

        return offeringService.createOffering(request);
    }

    // UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public OfferingResponse updateOffering(
            @PathVariable Long id,
            @RequestBody @Valid UpdateOfferingRequest request) {

        return offeringService.updateOffering(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public void deleteOffering(@PathVariable Long id) {
        offeringService.deleteOffering(id);
    }

    // GENERAL SUMMARY
    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public OfferingSummaryResponse getSummary() {
        return offeringService.getSummary();
    }

    // MONTHLY SUMMARY
    @GetMapping("/summary/monthly")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public OfferingMonthlySummaryResponse getMonthlySummary(
            @RequestParam int year,
            @RequestParam int month) {

        return offeringService.getMonthlySummary(year, month);
    }
}