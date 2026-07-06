package com.irarrazabal.iglesiaapi.application.service;

import com.irarrazabal.iglesiaapi.application.dto.dashboard.DashboardResponse;
import com.irarrazabal.iglesiaapi.domain.repository.MemberRepository;
import com.irarrazabal.iglesiaapi.domain.repository.MinistryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardService {


    private final MemberRepository memberRepository;
    private final MinistryRepository ministryRepository;

    public DashboardService(
            MemberRepository memberRepository,
            MinistryRepository ministryRepository
    ) {
        this.memberRepository = memberRepository;
        this.ministryRepository = ministryRepository;
    }
@Transactional(readOnly = true)
    public DashboardResponse getDashboard() {

        return new DashboardResponse(

                memberRepository.count(),
                memberRepository.countByActiveTrue(),
                memberRepository.countByActiveFalse(),
                ministryRepository.count(),
                memberRepository.countMemberByOffice(),
                ministryRepository.dashboarMinistryCount()
        );
    }
}
