package com.irarrazabal.iglesiaapi.domain.repository;

import com.irarrazabal.iglesiaapi.domain.model.Attendance;
import com.irarrazabal.iglesiaapi.domain.model.AttendenceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByMemberId(Long memberId);

    List<Attendance> findByServiceDate(LocalDate serviceDate);

    Long countByStatus(AttendenceStatus status);

    boolean existsByMemberIdAndServiceDate(
            Long memberId,
            LocalDate serviceDate
    );
}
