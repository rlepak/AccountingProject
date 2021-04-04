package com.project.implementation;

import com.project.dto.InvoiceDto;
import com.project.dto.ProductDto;
import com.project.entity.Invoice;
import com.project.entity.Product;
import com.project.enums.InvoiceType;
import com.project.exception.AccountingProjectException;
import com.project.repository.InvoiceRepository;
import com.project.service.InvoiceService;
import com.project.util.MapperUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private MapperUtil mapperUtil;
    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(MapperUtil mapperUtil, InvoiceRepository invoiceRepository) {
        this.mapperUtil = mapperUtil;
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public List<InvoiceDto> listAllInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        return invoiceList.stream().map(invoice -> mapperUtil.convert(invoice, new InvoiceDto())).collect(Collectors.toList());
    }

    @Override
    public InvoiceDto savePurchaseInvoice(InvoiceDto invoiceDto) throws AccountingProjectException {
        Invoice invoice = invoiceRepository.findById(invoiceDto.getId()).orElse(null);
        if(invoice!=null){
            throw  new AccountingProjectException("This invoice exist");
        }
        Invoice invoiceObject = mapperUtil.convert(invoiceDto, new Invoice());
        invoiceObject.setInvoiceType(InvoiceType.PURCHASE);

        Invoice savedInvoice = invoiceRepository.save(invoiceObject);
        return mapperUtil.convert(savedInvoice, new InvoiceDto());
    }

    @Override
    public InvoiceDto findById(Long id) throws AccountingProjectException {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice==null){
            throw new AccountingProjectException("Invoice with " + id + " not exist");
        }
        return mapperUtil.convert(invoice, new InvoiceDto());
    }

    @Override
    public InvoiceDto update(InvoiceDto invoiceDto) throws AccountingProjectException {
        Invoice invoice = invoiceRepository.findById(invoiceDto.getId()).orElse(null);
        Invoice updatedInvoice = mapperUtil.convert(invoiceDto, new Invoice());
        updatedInvoice.setId(invoice.getId());
        invoiceRepository.save(updatedInvoice);
        return findById(invoiceDto.getId());
    }

    @Override
    public List<InvoiceDto> findAllByInvoiceNumber(String invoiceNumber) {
        List<Invoice> listByInvoiceNumber = invoiceRepository.findAllByInvoiceNumber(invoiceNumber);
        return listByInvoiceNumber.stream().map(invoice -> mapperUtil.convert(invoice, new InvoiceDto())).collect(Collectors.toList());
    }
}
