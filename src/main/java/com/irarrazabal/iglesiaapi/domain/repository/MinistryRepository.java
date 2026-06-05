package com.irarrazabal.iglesiaapi.domain.repository;

import com.irarrazabal.iglesiaapi.application.dashboard.dto.DashboardMinistryCountResponse;
import com.irarrazabal.iglesiaapi.domain.model.Ministry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinistryRepository extends JpaRepository<Ministry, Long> {



    @Query("""
        SELECT new com.irarrazabal.iglesiaapi.application.dto.dashboard.DashboardMinistryCountResponse(
             m.name,
                COUNT(mem)
             )

          FROM Ministry m
          LEFT JOIN m.members mem
          GROUP BY m.name

        """)
    public List<DashboardMinistryCountResponse> dashboarMinistryCount();



}
