package com.adel.modularruleengine.model;

import lombok.*;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Entity
@OptimisticLocking
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "rule")
    private List<RuleMethod> methods;

    @ManyToOne
    private RuleGroup ruleGroup;

}
