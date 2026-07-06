package com.irarrazabal.iglesiaapi.domain.model;

import com.irarrazabal.iglesiaapi.domain.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "ministerios",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_ministry_name",
                        columnNames = "name"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ministry extends Auditable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @ManyToMany(mappedBy = "ministries", fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();



}
