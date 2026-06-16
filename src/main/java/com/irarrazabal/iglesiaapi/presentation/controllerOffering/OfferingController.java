package com.irarrazabal.iglesiaapi.presentation.controllerOffering;


import com.irarrazabal.iglesiaapi.application.dto.offering.*;
import com.irarrazabal.iglesiaapi.application.service.OfferingService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offering")
public class OfferingController {


    private final OfferingService offeringService;

    public OfferingController(OfferingService offeringService) {
        this.offeringService= offeringService;
    }


    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN','LIDER')")
    public List<OfferingResponse> getAllOffering(){
        return  offeringService.findAllOfferings();
    }


    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN','LIDER')")
    public  OfferingResponse findOfferingById(@PathVariable  Long id){
        return offeringService.findById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public OfferingResponse createOffering(@RequestBody CreateOfferingRequest request){
        return offeringService.createOffering(request);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public OfferingResponse updateOffering(@PathVariable Long id, @RequestBody @Valid UpdateOfferingRequest request){
        return offeringService.updateOffering(id,request);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public void deleteOffering(@PathVariable Long id){
        offeringService.deleteOffering(id);
    }

    @GetMapping("/summary/month")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public OfferingMonthlySummaryResponse getMonthlySummary(
            @RequestParam int year,
            @RequestParam int month
    ) {
        return offeringService.getMonthlySummary(year, month);
    }

    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public OfferingSummaryResponse getSummary() {
        return offeringService.getSummary();
    }




}
