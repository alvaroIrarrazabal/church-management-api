package com.irarrazabal.iglesiaapi.domain.repository;

import com.irarrazabal.iglesiaapi.application.dto.dashboard.DashboardOfficeCountResponse;
import com.irarrazabal.iglesiaapi.domain.model.EcclesiasticalOffice;
import com.irarrazabal.iglesiaapi.domain.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    List<Member> findByActiveTrue();

    List<Member> findByActiveFalse();

    // Dashboard
    long countByActiveTrue();

    long countByActiveFalse();

    long countByEcclesiasticalOffice(
            EcclesiasticalOffice ecclesiasticalOffice);


    @Query("""
            SELECT new com.irarrazabal.iglesiaapi.application.dto.dashboard.DashboardOfficeCountResponse(
               m.ecclesiasticalOffice,
            COUNT(m)
                    )
            FROM Member m
            GROUP BY m.ecclesiasticalOffice
                   

        """)
    List<DashboardOfficeCountResponse>countMemberByOffice();


    Page<Member> findByNameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name,
            String lastname,
            String email,
            Pageable  pageable
    );


    Page<Member> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Member> findByEcclesiasticalOffice(EcclesiasticalOffice ecclesiasticalOffice,
                                                    Pageable pageable);

}
