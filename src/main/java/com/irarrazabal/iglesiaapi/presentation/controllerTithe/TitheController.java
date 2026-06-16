package com.irarrazabal.iglesiaapi.presentation.controllerTithe;

import com.irarrazabal.iglesiaapi.application.dto.tithe.*;
import com.irarrazabal.iglesiaapi.application.service.TitheService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/tithe")
public class TitheController {

    private final TitheService titheService;
    public TitheController(TitheService titheService) {
        this.titheService = titheService;

    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAnyRole('PASTOR','LIDER','ADMIN')")
    public List<TitheResponse> findAllTithe(){
        return titheService.findAllTithes();
    }


    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public TitheResponse createTithe(@RequestBody @Valid CreateTitherequest createTitherequest){
        return titheService.createTithe(createTitherequest);
    }

    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN','LIDER')")
    public TitheResponse findById(@PathVariable Long id){
        return titheService.findTitheById(id);
    }
    @GetMapping("/findByMember/{memberId}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN','LIDER')")
    public List<TitheResponse> findByMember(@PathVariable Long memberId){
        return titheService.findByMemberId(memberId);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public void deleteTithe(@PathVariable Long id){
         titheService.deleteTitheById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public TitheResponse updateTithe(@PathVariable Long id, @RequestBody @Valid UpdateTitheRequest request){
        return titheService.updateTitheById(id, request);

    }

    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public TitheSummaryResponse getSummary(){
        return titheService.getSummary();

    }

    @GetMapping("/member/{memberId}/total")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public MemberTitheSummaryResponse getTotalByMember(@PathVariable Long memberId){
        return titheService.getTotalByMember(memberId);

    }


    @GetMapping("/summary/month")
    @PreAuthorize("hasAnyRole('PASTOR','ADMIN')")
    public TitheMonthlySummaryResponse getTotalByMonth(@RequestParam int year, @RequestParam int month){

        return titheService.getTotalByMonth(year, month);
    }



}
