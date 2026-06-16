package com.irarrazabal.iglesiaapi.domain.repository;

import com.irarrazabal.iglesiaapi.domain.model.Offering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OfferingRepository extends JpaRepository<Offering,Long> {



    List<Offering> findByServiceDate(LocalDate date);

    long countByServiceDateBetween(LocalDate startDate, LocalDate endDate);


    @Query("""
        SELECT COALESCE(SUM(o.amount),0)
        FROM Offering o
    
""")
    BigDecimal getTotalAmount();


    @Query("""
        SELECT COALESCE(SUM(o.amount),0)
        FROM Offering o
        WHERE o.serviceDate BETWEEN :startDate AND :endDate
""")
    BigDecimal getTotalAmountByDateRange(  @Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);


}
