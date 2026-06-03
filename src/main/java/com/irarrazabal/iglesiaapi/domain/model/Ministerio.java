package com.irarrazabal.iglesiaapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ministerios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ministerio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;

    @ManyToMany(mappedBy = "ministerios",  fetch = FetchType.LAZY)
    private List<Integrante> integrantes;




}
