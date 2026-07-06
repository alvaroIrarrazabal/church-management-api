package com.irarrazabal.iglesiaapi.domain.model;

import com.irarrazabal.iglesiaapi.domain.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "integrantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastname;



    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private boolean active;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EcclesiasticalOffice ecclesiasticalOffice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "integrante_ministerio",
            joinColumns = @JoinColumn(name = "integrante_id"),
            inverseJoinColumns = @JoinColumn(name = "ministerio_id")
    )
    private List<Ministry> ministries;
}