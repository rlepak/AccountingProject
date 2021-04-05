package com.project.implementation;

import com.project.dto.InvoiceProductDto;
import com.project.dto.ProductDto;
import com.project.entity.InvoiceProduct;
import com.project.entity.Product;
import com.project.exception.AccountingProjectException;
import com.project.repository.InvoiceRepository;
import com.project.repository.ProductInvoiceRepository;
import com.project.service.InvoiceProductService;
import com.project.service.InvoiceService;
import com.project.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private ProductInvoiceRepository productInvoiceRepository;
    private InvoiceRepository invoiceRepository;
    private MapperUtil mapperUtil;

    public InvoiceProductServiceImpl(ProductInvoiceRepository productInvoiceRepository, InvoiceRepository invoiceRepository, MapperUtil mapperUtil) {
        this.productInvoiceRepository = productInvoiceRepository;
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public List<InvoiceProductDto> findAllByInvoiceNumber(String invoiceNumber) {
        List<InvoiceProduct> invoiceProductList = productInvoiceRepository.findAllByInvoiceInvoiceNumber(invoiceNumber);
        return invoiceProductList.stream().map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDto())).collect(Collectors.toList());
    }

    @Override
    public InvoiceProductDto save(InvoiceProductDto invoiceProductDto) throws AccountingProjectException {
        InvoiceProduct invoiceProduct = productInvoiceRepository.findById(invoiceProductDto.getId()).orElse(null);
        if (invoiceProduct != null) {
            throw new AccountingProjectException("This invoice product exist");
        }
        InvoiceProduct invoiceProductObject = mapperUtil.convert(invoiceProductDto, new InvoiceProduct());
        InvoiceProduct savedInvoiceProduct = productInvoiceRepository.save(invoiceProductObject);
        return mapperUtil.convert(savedInvoiceProduct, new InvoiceProductDto());
    }

    @Override
    public InvoiceProductDto findById(Long id) throws AccountingProjectException {
        InvoiceProduct invoiceProduct = productInvoiceRepository.findById(id).orElse(null);
        if (invoiceProduct == null) {
            throw new AccountingProjectException("Product Invoice with " + id + " not exist");
        }
        return mapperUtil.convert(invoiceProduct, new InvoiceProductDto());
    }

    @Override
    public void deleteInvoiceProduct(Long id) throws AccountingProjectException {
        InvoiceProduct invoiceProduct = productInvoiceRepository.findById(id).orElse(null);
        if (invoiceProduct == null) {
            throw new AccountingProjectException("Invoice product with " + id + " not exist");
        }
        invoiceProduct.setIsDeleted(true);
        productInvoiceRepository.save(invoiceProduct);
    }
}
