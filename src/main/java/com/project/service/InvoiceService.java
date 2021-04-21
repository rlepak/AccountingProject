package com.project.service;

import com.project.dto.InvoiceDto;
import com.project.dto.ProductDto;
import com.project.exception.AccountingProjectException;

import java.util.List;

public interface InvoiceService {

    List<InvoiceDto> listAllInvoices();
    List<InvoiceDto> listAllPurchaseInvoices();
    List<InvoiceDto> listAllSaleInvoices();

    InvoiceDto savePurchaseInvoice(InvoiceDto invoiceDto) throws AccountingProjectException;
    InvoiceDto saveSaleInvoice(InvoiceDto invoiceDto) throws AccountingProjectException;
    InvoiceDto findById(Long id) throws AccountingProjectException;
    InvoiceDto update (InvoiceDto invoiceDto) throws AccountingProjectException;
    InvoiceDto findByInvoiceNumber(String invoiceNumber);
    void deleteByInvoiceNumber(String invoiceNumber) throws AccountingProjectException;
    InvoiceDto approveInvoice(InvoiceDto invoiceDto) throws AccountingProjectException;

}
