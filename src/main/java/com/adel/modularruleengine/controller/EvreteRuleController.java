package com.adel.modularruleengine.controller;

import com.adel.modularruleengine.dto.InvoiceDto;
import com.adel.modularruleengine.model.Customer;
import com.adel.modularruleengine.model.Invoice;
import com.adel.modularruleengine.service.DaoService;
import com.adel.modularruleengine.service.EvreteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/rule1")
public class EvreteRuleController {

    private final DaoService daoService;
    private final EvreteService evreteService;

    @PostMapping
    public Long addInvoice(final InvoiceDto invoiceDto){
        log.info("Add new Invoice :"+invoiceDto);
        return daoService.saveInvoice(invoiceDto);
    }

    @GetMapping
    public List<Customer> executeRule1(){
        log.info("Execute rule 1");

        final Collection<Object> sessionData = new ArrayList<>();
        final List<Invoice> allIUnprocessedInvoices = daoService.getAllIUnprocessedInvoices();
        final List<Customer> allCustomers = daoService.getAllCustomer();

        sessionData.addAll(Arrays.asList(allIUnprocessedInvoices.toArray()));
        sessionData.addAll(Arrays.asList(allCustomers.toArray()));

        evreteService.rule1()
                .newStatelessSession()
                .insert(sessionData)
                .fire();

        log.info(sessionData.toString());

        daoService.updateCustomers(sessionData.stream()
                .filter(f -> f instanceof Customer)
                .map(m -> (Customer) m)
                .collect(Collectors.toList()));

        daoService.updateInvoices(sessionData.stream()
                                    .filter(f->f instanceof Invoice)
                                    .map(m->(Invoice) m)
                                    .collect(Collectors.toList()));

        return daoService.getAllCustomer();
    }

}
