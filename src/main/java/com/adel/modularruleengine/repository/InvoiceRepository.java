package com.adel.modularruleengine.repository;

import com.adel.modularruleengine.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("select i from Invoice i where i.processed = false")
    List<Invoice> findAllUnprocessed();

}
