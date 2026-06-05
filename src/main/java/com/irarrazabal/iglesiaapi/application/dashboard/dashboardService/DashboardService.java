package com.irarrazabal.iglesiaapi.application.dashboard.dashboardService;

import com.irarrazabal.iglesiaapi.application.dashboard.dto.DashboardResponse;
import com.irarrazabal.iglesiaapi.domain.repository.MemberRepository;
import com.irarrazabal.iglesiaapi.domain.repository.MinistryRepository;
import org.springframework.stereotype.Service;

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

    public DashboardResponse getDashboard() {

        return new DashboardResponse(

                memberRepository.count(),
                memberRepository.countByAssetTrue(),
                memberRepository.countByAssetFalse(),
                ministryRepository.count(),
                memberRepository.countMemberByOffice(),
                ministryRepository.dashboarMinistryCount()
        );
    }
}
