package com.project.service;

import com.project.dto.InvoiceProductDto;
import com.project.dto.ProductDto;
import com.project.enums.InvoiceType;
import com.project.exception.AccountingProjectException;

import java.util.List;

public interface InvoiceProductService {


    List<InvoiceProductDto> findPurchaseSaleInvoiceByInvoiceNumber(String invoiceNumber);
    InvoiceProductDto savePurchaseSaleInvoice(InvoiceProductDto invoiceProductDto) throws AccountingProjectException;
    InvoiceProductDto findByIdPurchaseSaleInvoice(Long id) throws AccountingProjectException;
    void deletePurchaseSaleInvoiceProduct(Long id) throws AccountingProjectException;





}
