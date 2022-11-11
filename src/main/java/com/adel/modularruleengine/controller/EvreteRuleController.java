package com.adel.modularruleengine.controller;

import com.adel.modularruleengine.dto.InvoiceDto;
import com.adel.modularruleengine.model.Customer;
import com.adel.modularruleengine.model.Invoice;
import com.adel.modularruleengine.model.MessageCarrier;
import com.adel.modularruleengine.service.CustomSpringEventPublisher;
import com.adel.modularruleengine.service.DaoService;
import com.adel.modularruleengine.service.EvreteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class EvreteRuleController {

    private final DaoService daoService;
    private final EvreteService evreteService;
    private final CustomSpringEventPublisher customSpringEventPublisher;

    @PostMapping
    public Long addInvoice(final InvoiceDto invoiceDto){
        log.info("Add new Invoice :"+invoiceDto);
        return daoService.saveInvoice(invoiceDto);
    }

    @PostMapping("/random/{val}")
    public List<Long> addRandom(@PathVariable("val") final Integer val){

        final Stream<Integer> vals = IntStream.range(0, val).boxed();
        return vals.sequential().mapToLong(i->{
            final InvoiceDto invoiceDto = InvoiceDto.builder()
                    .customerName("Customer A")
                    .amount(BigDecimal.valueOf(Math.random()*100))
                    .build();
            log.info("Add new Invoice :"+invoiceDto);

            return daoService.saveInvoice(invoiceDto);
        }).boxed().collect(Collectors.toList());

    }

    @GetMapping("/rule1/{type}")
    public List<Customer> executeRule1(@PathVariable String type) throws ExecutionException, InterruptedException, IOException {

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        log.info("Execute rule 1");

        final Collection<Object> sessionData = new ArrayList<>();
        final CompletableFuture<List<Invoice>> allIUnprocessedInvoices = CompletableFuture.supplyAsync(() ->  daoService.getAllIUnprocessedInvoices());
        final CompletableFuture<List<Customer>> allCustomers = CompletableFuture.supplyAsync(() -> daoService.getAllCustomer());

        CompletableFuture.allOf(allIUnprocessedInvoices, allCustomers);

        sessionData.addAll(Arrays.asList(allIUnprocessedInvoices.get().toArray()));
        sessionData.addAll(Arrays.asList(allCustomers.get().toArray()));

        if(type.equals("a")) {
            evreteService.ruleA()
                    .newStatelessSession()
                    .insert(sessionData)
                    .fire();
        }else {
            evreteService.ruleB()
                    .newStatelessSession()
                    .insert(sessionData)
                    .fire();
        }

        log.info(sessionData.toString());

        daoService.updateInvoices(allIUnprocessedInvoices.get());
        daoService.updateCustomers(allCustomers.get());

        stopWatch.stop();
        log.info("Stopwatch(sec): "+stopWatch.getTotalTimeSeconds());
        return daoService.getAllCustomer();
    }

    @PostMapping("/publishevent")
    public void publishEvent(){
        customSpringEventPublisher.publishEvent(MessageCarrier.builder()
                .message("TEST")
                .build());
    }


}
