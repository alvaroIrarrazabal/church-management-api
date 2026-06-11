package com.irarrazabal.iglesiaapi.domain.model;


import com.irarrazabal.iglesiaapi.domain.Enum.AttendenceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="attendences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendence {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDate serviceDate;

    @Enumerated(EnumType.STRING)
    private AttendenceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
