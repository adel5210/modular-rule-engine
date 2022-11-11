package com.adel.modularruleengine.rules;

import com.adel.modularruleengine.model.Customer;
import com.adel.modularruleengine.model.Invoice;
import org.evrete.dsl.annotation.Rule;
import org.evrete.dsl.annotation.RuleSet;
import org.evrete.dsl.annotation.Where;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@RuleSet
public class rule1 {

    private static final Logger log = Logger.getLogger("rule1");

    @Rule
    @Where("$i.customer.name == $c.name")
    public void rule1b(Customer $c, Invoice $i){
        log.log(Level.INFO, "Rule1: "+ String.valueOf($c));
        $c.addAmount($i.getAmount());
        $i.setProcessed(true);
    }

}