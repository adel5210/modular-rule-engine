package com.adel.modularruleengine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RuleRequest{
    private String groupName;
    private RuleAttributes ruleAttributes;

}
