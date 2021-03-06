package com.project.implementation;

import com.project.dto.ProductDto;
import com.project.entity.Company;
import com.project.entity.Product;
import com.project.exception.AccountingProjectException;
import com.project.repository.CompanyRepository;
import com.project.repository.ProductRepository;
import com.project.service.ProductService;
import com.project.util.MapperUtil;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private MapperUtil mapperUtil;
    private ProductRepository productRepository;
    private CompanyRepository companyRepository;

    public ProductServiceImpl(MapperUtil mapperUtil, ProductRepository productRepository, CompanyRepository companyRepository) {
        this.mapperUtil = mapperUtil;
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<ProductDto> listAllProductsByCompanyId(Long id) {
        List<Product> productList = productRepository.findAllByCompanyId(id);
        return productList.stream().map(product -> mapperUtil.convert(product, new ProductDto())).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> listAllProducts() {
        List<Product> productList = productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return productList.stream().map(product -> mapperUtil.convert(product, new ProductDto())).collect(Collectors.toList());
    }

    @Override
    public ProductDto save(ProductDto productDto, Authentication authentication) throws AccountingProjectException {
        Product product = productRepository.findById(productDto.getId()).orElse(null);
        if(product!=null){
            throw  new AccountingProjectException("This product exist");
        }
        Product productObject = mapperUtil.convert(productDto, new Product());
        productObject.setCompany(companyRepository.findCompanyByUserEmail(authentication.getName()));
        Product savedProduct = productRepository.save(productObject);
        return mapperUtil.convert(savedProduct, new ProductDto());
    }

    @Override
    public ProductDto findById(Long id) throws AccountingProjectException {
        Product product = productRepository.findById(id).orElse(null);
        if (product==null){
            throw new AccountingProjectException("Product with " + id + " not exist");
        }
        return mapperUtil.convert(product, new ProductDto());
    }

    @Override
    public ProductDto update(ProductDto productDto) throws AccountingProjectException {
        Product product = productRepository.findById(productDto.getId()).orElse(null);
        Product updatedProduct = mapperUtil.convert(productDto, new Product());
        updatedProduct.setId(product.getId());
        productRepository.save(updatedProduct);
        return findById(productDto.getId());
    }
}
