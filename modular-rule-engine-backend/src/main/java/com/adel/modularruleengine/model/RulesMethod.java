package com.adel.modularruleengine.model;

import lombok.*;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Setter
@Entity
@OptimisticLocking
public class RulesMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long id;

    @ManyToOne
    private RulesGroup rulesGroup;

    private String methodName;

    private String ruleName;

    private String whereSet;

    private String parameters;

    private String ruleDescription;

    private String statement;

    private LocalDateTime dateTimeRegistered;

}
