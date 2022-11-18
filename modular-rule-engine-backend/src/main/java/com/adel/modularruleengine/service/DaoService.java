package com.adel.modularruleengine.service;

import com.adel.modularruleengine.dto.InvoiceDto;
import com.adel.modularruleengine.model.Customer;
import com.adel.modularruleengine.model.Invoice;
import com.adel.modularruleengine.model.RulesGroup;
import com.adel.modularruleengine.model.RulesMethod;
import com.adel.modularruleengine.repository.CustomerRepository;
import com.adel.modularruleengine.repository.InvoiceRepository;
import com.adel.modularruleengine.repository.RuleMethodRepository;
import com.adel.modularruleengine.repository.RuleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DaoService {
    private final CustomerRepository customerRepository;
    private final InvoiceRepository invoiceRepository;
    private final RuleRepository ruleRepository;
    private final RuleMethodRepository ruleMethodRepository;

    @Transactional(readOnly = true)
    public RulesGroup getGroupName(final String ruleName){
        return ruleRepository.findByName(ruleName);
    }

    @Transactional
    public RulesGroup saveRule(final RulesGroup rulesGroup){
        return ruleRepository.save(rulesGroup);
    }

    @Transactional
    public RulesMethod saveMethod(final RulesMethod rulesMethod){
        return ruleMethodRepository.save(rulesMethod);
    }

    @Transactional
    public Long saveInvoice(final InvoiceDto invoiceDto){

        Customer customer = Optional.ofNullable(customerRepository.findByNameIgnoreCase(invoiceDto.getCustomerName()))
                .orElse(Customer.builder()
                        .name(invoiceDto.getCustomerName())
                        .build());

        customer = customerRepository.save(customer);

        Invoice invoice = Invoice.builder()
                .customer(customer)
                .amount(invoiceDto.getAmount())
                .build();

        invoice = invoiceRepository.save(invoice);
        return invoice.getId();

    }

    @Transactional(readOnly = true)
    public List<Invoice> getAllIUnprocessedInvoices(){
        return invoiceRepository.findAllUnprocessed();
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    @Transactional
    public void updateCustomers(List<Customer> customers){
        customerRepository.saveAllAndFlush(customers);
    }

    @Transactional
    public void updateInvoices(List<Invoice> invoices) {
        invoiceRepository.saveAllAndFlush(invoices);
    }
}
