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
public class Ministry {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "ministries",  fetch = FetchType.LAZY)
    private List<Member> members;




}
