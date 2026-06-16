package com.irarrazabal.iglesiaapi.application.service;

import com.irarrazabal.iglesiaapi.application.dto.tithe.*;
import com.irarrazabal.iglesiaapi.domain.model.Member;
import com.irarrazabal.iglesiaapi.domain.model.Tithe;
import com.irarrazabal.iglesiaapi.domain.repository.MemberRepository;
import com.irarrazabal.iglesiaapi.domain.repository.TitheRepository;
import com.irarrazabal.iglesiaapi.exceptions.MemberNotFoundExceptions;
import com.irarrazabal.iglesiaapi.exceptions.TitheNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TitheService {


    private TitheRepository titheRepository;
    private MemberRepository memberRepository;

    public TitheService(TitheRepository titheRepository, MemberRepository memberRepository) {
        this.titheRepository = titheRepository;
        this.memberRepository = memberRepository;
    }



    public List<TitheResponse> findAllTithes(){
        return titheRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public TitheResponse createTithe(CreateTitherequest request){

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new MemberNotFoundExceptions("El integrante no fue encontrado"));

        Tithe tithe = new Tithe();
        tithe.setMember(member);
        tithe.setAmount(request.amount());
        tithe.setDate(request.date());
        tithe.setNote(request.note());
        Tithe saved = titheRepository.save(tithe);
        return toResponse(saved);

    }

    public TitheResponse findTitheById(Long id){

        Tithe tithe = titheRepository.findById(id)
                .orElseThrow(() -> new TitheNotFoundException("El Diezmo no existe"));

        return toResponse(tithe);
    }

    public List<TitheResponse> findByMemberId(Long memberId){
         return titheRepository.findByMemberId(memberId)
                 .stream().map(this::toResponse)
                 .toList();
    }


    public void deleteTitheById(Long id){
        if(!titheRepository.existsById(id)){
            throw new TitheNotFoundException("El Diezmo no existe");
        }
        titheRepository.deleteById(id);
    }

    public TitheResponse updateTitheById(Long id, UpdateTitheRequest request){

        Tithe tithe = titheRepository.findById(id)
                .orElseThrow(() -> new TitheNotFoundException("El Diezmo no existe"));

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new MemberNotFoundExceptions("El Integrante no existe"));

        tithe.setMember(member);
        tithe.setAmount(request.amount());
        tithe.setDate(request.date());
        tithe.setNote(request.note());

        Tithe updated = titheRepository.save(tithe);

        return toResponse(updated);

    }





    private TitheResponse toResponse(Tithe tithe){

        return new TitheResponse(
                tithe.getId(),
                tithe.getMember().getId(),
                tithe.getMember().getName() + " " +
                tithe.getMember().getLastname(),
                tithe.getAmount(),
                tithe.getDate(),
                tithe.getNote()


        );
    }


    public TitheSummaryResponse getSummary(){

        return new TitheSummaryResponse(
                titheRepository.getTotalAmount(),
                titheRepository.count(),
                titheRepository.countDistinctMembers()
        );

    }

    public MemberTitheSummaryResponse getTotalByMember(Long memberId){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundExceptions("El integrante no existe"));


        BigDecimal totalTithes = titheRepository.countByMemberId(memberId);

        BigDecimal totalAmount =
                titheRepository.totalTithesByMember(memberId);

        return new MemberTitheSummaryResponse(
                member.getId(),
                member.getName()+" "+member.getLastname(),
                totalTithes,
                totalAmount
        );

        }



public TitheMonthlySummaryResponse getTotalByMonth(
        int year,
        int month
) {

    LocalDate startDate = LocalDate.of(year, month, 1);

    LocalDate endDate = startDate.withDayOfMonth(
            startDate.lengthOfMonth()
    );

    return new TitheMonthlySummaryResponse(
            year,
            month,
            titheRepository.totalTithesByDateRange(
                    startDate,
                    endDate
            ),
            titheRepository.countByDateBetween(
                    startDate,
                    endDate
            )
    );
}




}
