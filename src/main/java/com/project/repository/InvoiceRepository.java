package com.project.repository;

import com.project.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByInvoiceNumber(String invoiceNumber);

}
