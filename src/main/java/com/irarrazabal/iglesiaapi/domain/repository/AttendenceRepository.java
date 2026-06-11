package com.irarrazabal.iglesiaapi.domain.repository;

import com.irarrazabal.iglesiaapi.domain.Enum.AttendenceStatus;
import com.irarrazabal.iglesiaapi.domain.model.Attendence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendenceRepository extends JpaRepository<Attendence, Long> {



    List<Attendence> findByMemberId(Long memberId);

    List<Attendence> findByServiceDate(LocalDate serviceDate);

    Long countByStatus(AttendenceStatus status);

    boolean existsByMemberIdAndServiceDate(
            Long memberId,
            LocalDate serviceDate
    );

}
