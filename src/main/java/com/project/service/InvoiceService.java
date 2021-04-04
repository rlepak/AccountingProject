package com.project.service;

import com.project.dto.InvoiceDto;
import com.project.dto.ProductDto;
import com.project.exception.AccountingProjectException;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDto> listAllInvoices();
    InvoiceDto savePurchaseInvoice(InvoiceDto invoiceDto) throws AccountingProjectException;
    InvoiceDto findById(Long id) throws AccountingProjectException;
    InvoiceDto update (InvoiceDto invoiceDto) throws AccountingProjectException;
    List<InvoiceDto> findAllByInvoiceNumber(String invoiceNumber);
}
