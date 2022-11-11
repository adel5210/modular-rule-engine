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
import java.util.logging.Level;
import java.util.logging.Logger;


@RuleSet
public class ruleTemplate {

    private static final Logger log = Logger.getLogger("rule_processor");
    // start of custom code

    /*
    // @{_startMethod}
    // --> START_@{ruleMethodName}
    // ========== @{ruleDescription} ==========
    // ========== @{_dateRegistered} ==========
    @Rule("@{ruleName}")
    @Where("@{where}")
    public void @{ruleMethodName}(@{parameters}){
        log.log(Level.INFO, String.valueOf("@{ruleMethodName}"));
        @{statement}
    }
    // -->  END_@{ruleMethodName}
    // @{_endMethod}
    */

    // --> START_String
    // ========== String ==========
    // ========== 2022-11-11T23:07:31.890496313 ==========
    @Rule("String")
    @Where("String")
    public void String(String){
        log.log(Level.INFO, String.valueOf("String"));
        String
    }
    // -->  END_String
// @{_next}

    // end of custom code

}
