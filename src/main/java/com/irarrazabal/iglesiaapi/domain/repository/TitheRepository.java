package com.irarrazabal.iglesiaapi.domain.repository;

import com.irarrazabal.iglesiaapi.domain.model.Tithe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TitheRepository extends JpaRepository<Tithe, Long> {

    public List<Tithe> findByMemberId(Long memberId);

    @Query(
            """
    SELECT COALESCE(SUM(t.amount), 0)
    FROM Tithe t
""")
    BigDecimal getTotalAmount();



    @Query("""
        SELECT COALESCE(SUM(t.amount),0)
        FROM Tithe t 
        WHERE t.member.id = :memberId

""")
    BigDecimal totalTithesByMember( @Param("memberId") Long memberId);

    @Query("""
    SELECT COALESCE(SUM(t.amount), 0)
    FROM Tithe t
    WHERE t.date BETWEEN :startDate AND :endDate
""")
    BigDecimal totalTithesByPeriod(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );


    @Query("""
            SELECT COUNT(DISTINCT t.member.id)
            FROM Tithe t
            """)
    long countDistinctMembers();

    BigDecimal countByMemberId(Long memberId);

    long countByDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );


    @Query("""
    SELECT COALESCE(SUM(t.amount), 0)
    FROM Tithe t
    WHERE t.date BETWEEN :startDate AND :endDate
""")
    BigDecimal totalTithesByDateRange(
            LocalDate startDate,
            LocalDate endDate
    );
}
