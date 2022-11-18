package com.adel.modularruleengine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RuleAttributes {

    /**
     * must align with RuleAttributeTypes
     */

    private String ruleName;
    private String where;
    private String ruleMethodName;
    private String parameters;
    private String ruleDescription;
    private String statement;
    private String _dateRegistered;

}
