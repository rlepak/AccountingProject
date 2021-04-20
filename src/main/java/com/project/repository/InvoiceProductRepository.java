package com.project.repository;

import com.project.dto.InvoiceProductDto;
import com.project.entity.InvoiceProduct;
import com.project.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    List<InvoiceProduct> findAllByInvoiceId(Long id);
    List<InvoiceProduct> findAllByInvoiceInvoiceNumber(String invoiceNumber);

    @Query(value = "select sum (unit_price*quantity) from invoice_product where invoice_id = ?1", nativeQuery = true)
    Long totalSumByInvoiceNumber(Long invoiceId);

}
