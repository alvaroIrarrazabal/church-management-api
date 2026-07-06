package com.irarrazabal.iglesiaapi.domain.model;

import com.irarrazabal.iglesiaapi.domain.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="attendances",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id","service_date"}

                )
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance extends Auditable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_date", nullable = false)
    private LocalDate serviceDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AttendenceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;


}