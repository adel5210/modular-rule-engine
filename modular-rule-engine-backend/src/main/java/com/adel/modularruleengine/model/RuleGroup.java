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
public class RuleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String groupName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "ruleGroup")
    private List<Rule> rules;

}
