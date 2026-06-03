package com.irarrazabal.iglesiaapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="integrantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Integrante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private LocalDate fechanacimiento;
    private boolean activo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "integrante_ministerio",
            joinColumns = @JoinColumn(name = "integrante_id"),
            inverseJoinColumns = @JoinColumn(name = "ministerio_id")
    )
    private List<Ministerio> ministerios;






}
