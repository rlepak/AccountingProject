package com.project.implementation;

import com.project.dto.InvoiceDto;
import com.project.dto.ProductDto;
import com.project.entity.Invoice;
import com.project.entity.InvoiceProduct;
import com.project.entity.Product;
import com.project.entity.User;
import com.project.enums.InvoiceType;
import com.project.enums.Status;
import com.project.exception.AccountingProjectException;
import com.project.repository.InvoiceProductRepository;
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
    private InvoiceProductRepository invoiceProductRepository;

    public InvoiceServiceImpl(MapperUtil mapperUtil, InvoiceRepository invoiceRepository, InvoiceProductRepository invoiceProductRepository) {
        this.mapperUtil = mapperUtil;
        this.invoiceRepository = invoiceRepository;
        this.invoiceProductRepository = invoiceProductRepository;
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
        invoiceObject.setStatus(Status.ACTIVE);
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
    public InvoiceDto findByInvoiceNumber(String invoiceNumber) {
        Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber);
        return mapperUtil.convert(invoice, new InvoiceDto());
    }

    @Override
    public void deleteByInvoiceNumber(String invoiceNumber) throws AccountingProjectException {
        Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber);
        if (invoice == null) {
            throw new AccountingProjectException("Invoice with " + invoiceNumber + " not exist");
        }

        List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllByInvoiceInvoiceNumber(invoiceNumber);
        invoiceProductList.forEach(i->i.setIsDeleted(true));
        invoice.setIsDeleted(true);
        invoiceRepository.save(invoice);
    }

    @Override
    public InvoiceDto approveInvoice(InvoiceDto invoiceDto) throws AccountingProjectException {
        Invoice invoice = invoiceRepository.findById(invoiceDto.getId()).orElse(null);
        invoice.setId(invoice.getId());
        invoice.setStatus(Status.CLOSED);
        invoiceRepository.save(invoice);
        return findById(invoiceDto.getId());
    }
}
