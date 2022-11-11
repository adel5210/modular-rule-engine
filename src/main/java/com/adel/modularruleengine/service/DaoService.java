package com.adel.modularruleengine.service;

import com.adel.modularruleengine.dto.InvoiceDto;
import com.adel.modularruleengine.model.Customer;
import com.adel.modularruleengine.model.Invoice;
import com.adel.modularruleengine.repository.CustomerRepository;
import com.adel.modularruleengine.repository.InvoiceRepository;
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

    public void updateCustomers(final List<Customer> customers){
        customerRepository.saveAll(customers);
    }

    public void updateInvoices(List<Invoice> invoices) {
        invoiceRepository.saveAll(invoices);
    }
}
