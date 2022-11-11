package com.adel.modularruleengine.rules;

import com.adel.modularruleengine.model.Customer;
import com.adel.modularruleengine.model.Invoice;
import org.evrete.dsl.annotation.Rule;
import org.evrete.dsl.annotation.RuleSet;
import org.evrete.dsl.annotation.Where;

import java.math.BigDecimal;
import java.math.*;
import java.lang.*;
import java.util.*;
import java.time.*;

@RuleSet
public class ruleTemplate {

    // start of custom code

    @{startMethod}
    // ========== @{ruleDescription} ==========
    // ========== @{_dateRegistered} ==========
    @Rule("@{ruleName}")
    @Where("@{where}")
    public void @{ruleMethodName}(@{conditions}){
        @{statement}
    }

    @{endMethod}
    @{_next}

    // end of custom code

}