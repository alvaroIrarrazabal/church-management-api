package com.irarrazabal.iglesiaapi.domain.model;

import com.irarrazabal.iglesiaapi.domain.Enum.EcclesiasticalOffice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="integrantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private LocalDate birthdate;
    private boolean asset;

    @Enumerated(EnumType.STRING)
    private EcclesiasticalOffice ecclesiasticalOffice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "integrante_ministerio",
            joinColumns = @JoinColumn(name = "integrante_id"),
            inverseJoinColumns = @JoinColumn(name = "ministerio_id")
    )
    private List<Ministry> ministries;






}
