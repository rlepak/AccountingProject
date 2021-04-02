package com.project.service;

import com.project.dto.CompanyDto;
import com.project.dto.ProductDto;
import com.project.exception.AccountingProjectException;

import java.util.List;

public interface ProductService {
    List<ProductDto> listAllProducts();
    ProductDto save(ProductDto productDto) throws AccountingProjectException;
    ProductDto findById(Long id) throws AccountingProjectException;
    ProductDto update (ProductDto productDto) throws AccountingProjectException;
}
