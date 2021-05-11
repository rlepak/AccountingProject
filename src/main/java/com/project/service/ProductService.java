package com.project.service;

import com.project.dto.CategoryDto;
import com.project.dto.CompanyDto;
import com.project.dto.ProductDto;
import com.project.exception.AccountingProjectException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProductService {
    List<ProductDto> listAllProducts();
    List<ProductDto> listAllProductsByCompanyId(Long id);
    ProductDto save(ProductDto productDto, Authentication authentication) throws AccountingProjectException;
    ProductDto findById(Long id) throws AccountingProjectException;
    ProductDto update (ProductDto productDto) throws AccountingProjectException;
}
