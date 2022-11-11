package com.adel.modularruleengine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RuleAttributes {

    private String ruleName;
    private String where;
    private String ruleMethodName;
    private String parameters;
    private String ruleDescription;
    private String statement;

}
