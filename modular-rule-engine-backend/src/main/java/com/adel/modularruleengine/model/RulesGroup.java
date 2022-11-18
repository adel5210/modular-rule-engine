package com.adel.modularruleengine.model;

import lombok.*;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Entity
@OptimisticLocking
public class RulesGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(unique = true)
    private String groupName;

    @Builder.Default
    @OneToMany(mappedBy = "rulesGroup")
    private List<RulesMethod> methods = new ArrayList<>();

}
