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
    // @{_countRule}
    // start of custom code

    /*
    // @{_startMethod}
    // ========== @{ruleDescription} ==========
    // ========== @{_dateRegistered} ==========
    @Rule("@{ruleName}")
    @Where("@{where}")
    public void @{ruleMethodName}(@{parameters}){
        log.log(Level.INFO, String.valueOf("@{ruleMethodName}"));
        @{statement}
    }
    // @{_endMethod}
    */

    // ========== NONE ==========
    // ========== @{_dateRegistered} ==========
    @Rule("ruleOne")
    @Where("$i==$c")
    public void ruleOne(String c){
        log.log(Level.INFO, String.valueOf("ruleOne"));
        return;
    }
    // ========== NONE ==========
    // ========== @{_dateRegistered} ==========
    @Rule("ruleOne")
    @Where("$i==$c")
    public void ruleOne(String c){
        log.log(Level.INFO, String.valueOf("ruleOne"));
        return;
    }
    // ========== NONE ==========
    // ========== @{_dateRegistered} ==========
    @Rule("ruleOne")
    @Where("$i==$c")
    public void ruleOne(String c){
        log.log(Level.INFO, String.valueOf("ruleOne"));
        return;
    }
// @{_next}

    // end of custom code

}
