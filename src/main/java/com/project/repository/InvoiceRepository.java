package com.project.repository;

import com.project.entity.Invoice;
import com.project.entity.InvoiceProduct;
import com.project.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findByInvoiceNumber(String invoiceNumber);
    List<Invoice> findAllByInvoiceType(InvoiceType invoiceType);





}
