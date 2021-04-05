package com.project.repository;

import com.project.dto.InvoiceProductDto;
import com.project.entity.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    List<InvoiceProduct> findAllByInvoiceId(Long id);
    List<InvoiceProduct> findAllByInvoiceInvoiceNumber(String invoiceNumber);


}
