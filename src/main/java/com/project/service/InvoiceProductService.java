package com.project.service;

import com.project.dto.InvoiceProductDto;
import com.project.dto.ProductDto;
import com.project.exception.AccountingProjectException;

import java.util.List;

public interface InvoiceProductService {

    List<InvoiceProductDto> findAllByInvoiceId(long id);
    InvoiceProductDto save(InvoiceProductDto invoiceProductDto) throws AccountingProjectException;
    InvoiceProductDto findById(Long id) throws AccountingProjectException;


}
