package com.irarrazabal.iglesiaapi.application.attendance.service;

import com.irarrazabal.iglesiaapi.application.attendance.dto.AttendenceResponse;
import com.irarrazabal.iglesiaapi.application.attendance.dto.CreateAttendenceRequest;
import com.irarrazabal.iglesiaapi.domain.model.Attendence;
import com.irarrazabal.iglesiaapi.domain.model.Member;
import com.irarrazabal.iglesiaapi.domain.repository.AttendenceRepository;
import com.irarrazabal.iglesiaapi.domain.repository.MemberRepository;
import com.irarrazabal.iglesiaapi.exceptions.custom.AttendenceNotFoundExceptions;
import com.irarrazabal.iglesiaapi.exceptions.custom.MemberNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendenceService {



    private final AttendenceRepository attendenceRepository;
    private final MemberRepository memberRepository;

    public AttendenceService(AttendenceRepository attendenceRepository, MemberRepository memberRepository) {
        this.attendenceRepository = attendenceRepository;
        this.memberRepository = memberRepository;
    }


    public AttendenceResponse createAttendence(CreateAttendenceRequest request)
    {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(()->new MemberNotFoundExceptions("Integrante no encontado"));

        if(attendenceRepository
                .existsByMemberIdAndServiceDate(
                        request.memberId(),
                        request.serviceDate()
                )) {

            throw new AttendenceNotFoundExceptions(
                    "La asistencia ya fue registrada"
            );
        }


        Attendence attendence = new Attendence();

        attendence.setMember(member);
        attendence.setServiceDate(request.serviceDate());
        attendence.setStatus(request.status());



        Attendence saved =  attendenceRepository.save(attendence);

        return toResponse(attendence);

    }

    //buscar por miembros
    public List<AttendenceResponse> findByMember(Long memberId)
    {
        return attendenceRepository.findByMemberId(memberId)
                .stream()
                .map(this::toResponse
                ).toList();
    }

    //buscar por fecha

    public List<AttendenceResponse> findByDate(LocalDate date){
        return  attendenceRepository.findByServiceDate(date)
                .stream()
                .map(this::toResponse)
                .toList();

    }









    public AttendenceResponse toResponse(Attendence attendence) {
        return new AttendenceResponse(
                attendence.getId(),
                attendence.getMember().getName()+ " "+attendence.getMember().getLastname(),
                attendence.getServiceDate(),
                attendence.getStatus()
        );

    }



}
