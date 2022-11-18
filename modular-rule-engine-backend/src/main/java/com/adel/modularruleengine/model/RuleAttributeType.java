package com.adel.modularruleengine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RuleAttributeType {

    RULE_METHOD_NAME("@{ruleMethodName}", false),
    RULE_DESCRIPTION("@{ruleDescription}", false),
    DATE_REGISTERED("@{_dateRegistered}", true),
    RULE_NAME("@{ruleName}", false),
    WHERE("@{where}", false),
    PARAMETERS("@{parameters}", false),
    STATEMENT("@{statement}", false),
    START_METHOD("@{_startMethod}", true),
    END_METHOD("@{_endMethod}", true),
    NEXT("@{_next}", true)
    ;

    private final String property;
    private final boolean hidden;

    public static String getWrap(final String field){
        return "@{" + field + "}";
    }
}
