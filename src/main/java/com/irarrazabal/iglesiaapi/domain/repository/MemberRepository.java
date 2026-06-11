package com.irarrazabal.iglesiaapi.domain.repository;

import com.irarrazabal.iglesiaapi.application.dashboard.dto.DashboardOfficeCountResponse;
import com.irarrazabal.iglesiaapi.domain.Enum.EcclesiasticalOffice;
import com.irarrazabal.iglesiaapi.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByEcclesiasticalOffice(
            EcclesiasticalOffice ecclesiasticalOffice
    );



    List<Member> findByMinistries_Name(String name);

    List<Member> findByAssetTrue();

    List<Member> findByAssetFalse();

    // Dashboard
    long countByAssetTrue();

    long countByAssetFalse();

    long countByEcclesiasticalOffice(
            EcclesiasticalOffice ecclesiasticalOffice);


    @Query("""
            SELECT new com.irarrazabal.iglesiaapi.application.dashboard.dto.DashboardOfficeCountResponse(
               m.ecclesiasticalOffice,
            COUNT(m)
                    )
            FROM Member m
            GROUP BY m.ecclesiasticalOffice
                   

        """)
    List<DashboardOfficeCountResponse>countMemberByOffice();

}
