package com.adel.modularruleengine.service;

import com.adel.modularruleengine.model.Customer;
import com.adel.modularruleengine.model.Invoice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evrete.KnowledgeService;
import org.evrete.api.Knowledge;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Component
@Slf4j
@AllArgsConstructor
public class EvreteService {

    private final KnowledgeService knowledgeService;

    public Knowledge rule1(){
        return knowledgeService
                .newKnowledge()

//                .newRule("Clear total sales")
//                .forEach("$c", Customer.class)
//                .execute(ctx -> {
//                    final Customer c = ctx.get("$c");
//                    c.setWalletAmount(BigDecimal.ZERO);
//                })

                .newRule("Compute totals")
                .forEach("$c", Customer.class,
                        "$i", Invoice.class)
                .where("$i.customer.name == $c.name")
                .execute(ctx -> {
                    final Customer c = ctx.get("$c");
                    final Invoice i = ctx.get("$i");
                    c.addAmount(i.getAmount());
                    i.setProcessed(true);
                })

                ;

    }

}
