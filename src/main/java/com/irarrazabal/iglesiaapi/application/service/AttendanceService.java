package com.irarrazabal.iglesiaapi.application.service;



import com.irarrazabal.iglesiaapi.application.dto.attendance.AttendanceResponse;
import com.irarrazabal.iglesiaapi.application.dto.attendance.CreateAttendanceRequest;
import com.irarrazabal.iglesiaapi.domain.model.Attendance;
import com.irarrazabal.iglesiaapi.domain.model.Member;
import com.irarrazabal.iglesiaapi.domain.repository.AttendanceRepository;
import com.irarrazabal.iglesiaapi.domain.repository.MemberRepository;


import com.irarrazabal.iglesiaapi.exceptions.AttendanceNotFoundExceptions;
import com.irarrazabal.iglesiaapi.exceptions.MemberNotFoundExceptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, MemberRepository memberRepository) {
        this.attendanceRepository = attendanceRepository;
        this.memberRepository = memberRepository;
    }

@Transactional
    public AttendanceResponse createAttendence(CreateAttendanceRequest request)
    {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(()->new MemberNotFoundExceptions("Integrante no encontado"));

        if(attendanceRepository
                .existsByMemberIdAndServiceDate(
                        request.memberId(),
                        request.serviceDate()
                )) {

            throw new AttendanceNotFoundExceptions(
                    "La asistencia ya fue registrada"
            );
        }


        Attendance attendance = new Attendance();

        attendance.setMember(member);
        attendance.setServiceDate(request.serviceDate());
        attendance.setStatus(request.status());



        Attendance saved =  attendanceRepository.save(attendance);

        return toResponse(attendance);

    }

    //buscar por miembros
    @Transactional(readOnly = true)
    public List<AttendanceResponse> findByMember(Long memberId)
    {
        return attendanceRepository.findByMemberId(memberId)
                .stream()
                .map(this::toResponse
                ).toList();
    }

    //buscar por fecha
@Transactional(readOnly = true)
    public List<AttendanceResponse> findByDate(LocalDate date){
        return  attendanceRepository.findByServiceDate(date)
                .stream()
                .map(this::toResponse)
                .toList();

    }




    public AttendanceResponse toResponse(Attendance attendance) {
        return new AttendanceResponse(
                attendance.getId(),
                attendance.getMember().getName()+ " "+ attendance.getMember().getLastname(),
                attendance.getServiceDate(),
                attendance.getStatus()
        );

    }

}
