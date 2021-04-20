package com.project.implementation;

import com.project.dto.InvoiceProductDto;
import com.project.entity.InvoiceProduct;
import com.project.enums.InvoiceType;
import com.project.exception.AccountingProjectException;
import com.project.repository.InvoiceRepository;
import com.project.repository.InvoiceProductRepository;
import com.project.service.InvoiceProductService;
import com.project.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private InvoiceProductRepository invoiceProductRepository;
    private InvoiceRepository invoiceRepository;
    private MapperUtil mapperUtil;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, InvoiceRepository invoiceRepository, MapperUtil mapperUtil) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public List<InvoiceProductDto> findPurchaseSaleInvoiceByInvoiceNumber(String invoiceNumber) {
        List<InvoiceProduct> invoiceProductList = invoiceProductRepository.findAllByInvoiceInvoiceNumber(invoiceNumber);
        return invoiceProductList.stream().map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDto())).collect(Collectors.toList());
    }

    @Override
    public InvoiceProductDto savePurchaseSaleInvoice(InvoiceProductDto invoiceProductDto) throws AccountingProjectException {
        InvoiceProduct purchaseInvoiceList = invoiceProductRepository.findById(invoiceProductDto.getId()).orElse(null);
        if (purchaseInvoiceList != null) {
            throw new AccountingProjectException("This invoice product exist");
        }
        InvoiceProduct invoiceProductObject = mapperUtil.convert(invoiceProductDto, new InvoiceProduct());
        InvoiceProduct savedInvoiceProduct = invoiceProductRepository.save(invoiceProductObject);
        return mapperUtil.convert(savedInvoiceProduct, new InvoiceProductDto());
    }

    @Override
    public InvoiceProductDto findByIdPurchaseSaleInvoice(Long id) throws AccountingProjectException {
        InvoiceProduct invoiceProduct = invoiceProductRepository.findById(id).orElse(null);
        if (invoiceProduct == null) {
            throw new AccountingProjectException("Product Invoice with " + id + " not exist");
        }
        return mapperUtil.convert(invoiceProduct, new InvoiceProductDto());
    }

    @Override
    public void deletePurchaseSaleInvoiceProduct(Long id) throws AccountingProjectException {
        InvoiceProduct invoiceProduct = invoiceProductRepository.findById(id).orElse(null);
        if (invoiceProduct == null) {
            throw new AccountingProjectException("Invoice product with " + id + " not exist");
        }
        invoiceProduct.setIsDeleted(true);
        invoiceProductRepository.save(invoiceProduct);
    }


}
